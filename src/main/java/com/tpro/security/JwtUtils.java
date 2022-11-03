package com.tpro.security;
import java.util.Date;

import org.aspectj.apache.bcel.classfile.Code;
import org.hibernate.tool.schema.extract.internal.SequenceInformationExtractorMariaDBDatabaseImpl;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.tpro.security.service.UserDetailsImpl;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component //@Component ile kendi binizmi urettik  kendimiz urettigimiz icin @Component olarak atadik
public class JwtUtils {  // sabit spring boot veya spring security'nin methodlarini kullanmis olsaydik  
	                      //@Bean olarak atamamiz gerekirdi
	private String jwtSecret = "batch82";  // Bu screat key olusturduk
	
	// 24*60*60*1000 =  86400000 yapti
	private long jwtExpirationMs = 86400000; // BURADA JWT token mili saniye cinsinden hesaplanir bizde burada
	                                           // zamaninin kac milisaniye olacagini ayarladik 1 gun cin ayarladik
	
	
	
	
	//************* GENERATE -- TOKEN  ************
	
	//Authentication'i (import org.springframework.security.core.Authentication;) import ettik 
public String generateToken(Authentication authentication) {  // String data type vermemizin nedeni String manupule edebilmekti
		                                                      // integear matematik olarak hesplanacak birsey olsaydi Integear yapardik
	
	
	
	
	                                                           
		//********* Anlık olarak login olarak kullanıcının bilgisini alıyorum****************
	
	/* Altta olusturdugumuz (UserDetailsImpl)methodu'nu  Security olarak kullanabilmemiz icin (Spring Security'nin)
	 bizden kullanmamizi istedigi method olan (UserDetails) methodunu kullanabilmek icin 
	(UserDetails) metodunu bska bi class olarak olusturacagimiz
	(UserDetailsImpl) class implement edip onun icindeki methodlari kullanmamiz gerek.*/
	
	
		    UserDetailsImpl userDetails  = (UserDetailsImpl) authentication.getPrincipal(); 
		  /* (authentication.getPrincipal();) anlik olarak giren kullanicinin bilgileri icin bu methodu kullaniyoruz
		  bundan dolayi yukarda olusturdubgumuz methodun patameresine (Authentication)'i ekleyip import ettik
		  simdi boylece kullanici girdiginde hemen onun bilgilerine ulasabilecegiz
		  spring security bize bunu authentication.getPrincipal() methodu ile ulastiriyor*/
		    
		    
		 
		    
		   //*********** Simdi Token uretmemizgerek********
		    
		   /* Token olusturabilmemizicin icin POM XML'e bir (dependency) yuklememiz lazim 
		    /*<dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt</artifactId>
            <version>0.9.1</version>
        </dependency>
		    Bu dependency Code yazmadan (jjwt'yi) generate ve validate ediyor
		     Alttaki code lar bize bu dependency'i ile beraber hazir geliyor ve kullanabiliryoruz*/
		    
		    /* Tokenlar builder() ile üretiliyor ve Jwts.builder() .setSubject() methodlari kullaniyoruz
		    token üretilirken UserName ve secret key kullanılır ve alttaki kullandigimiz 
		     signWith() methodu icine su sekilde (signWith(SignatureAlgorithm.HS512, jwtSecret)
		      parametre olarak verdik boylece Token icin gerekli adimlari atmis olduk*/
		 return Jwts.builder().
				 setSubject(userDetails.getUsername()).                        // JWT Token'nin icerisine  userName'i koyduk                   
				setIssuedAt(new Date()).                                       // Token ne zaman uretildi onu tarihledik
				setExpiration(new Date(new Date().getTime()+ jwtExpirationMs)).// Token nekadar  zaman sonro bitecek onu tarihledik (jwtExpirationMs)  ile yukarida methodu olusturup 1 gun yani 86400000milisaniye olarak ayarladik ve buraya atadik 
				signWith(SignatureAlgorithm.HS512, jwtSecret).  //SignatureAlgorithm.HS512 hazir sifreleme ile sifreledik ve yanina jwtSecret key'imizi ekledik 
				compact();                                       // buradada compact() ile hepsini topla dedik yani Tokeni tamamla dedik
	}

//**********************************************************************

//************** VALIDATE-- TOKEN ****************
public boolean validateToken(String token) {
	
	
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
			
		} catch (ExpiredJwtException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedJwtException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedJwtException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SignatureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		}
//*******************************************************
//********** JWt tokenden userName'i alalım
	public String getUserNameFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
	}
	
	
	
	
	
	
}


