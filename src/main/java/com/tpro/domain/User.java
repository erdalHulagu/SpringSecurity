package com.tpro.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



/* Not = @Data annotationunun icerisinde bircok annotationlar(
@Getter, @Setter @RequiredArgsConstructor @ToString, @EqualsAndHashCode, @lombok.Value)
vardir icindeki annotationlar kullanilacaksa ihtiyac varsa 
 @Data annotationu kullanilmasi mantiklidir. ama icindeki annotationlarin hepsine ihtiyacimiz yoksa 
 kullanmamak daha mantikli cunku sistemi yavaslatir */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@RequiredArgsConstructor Bu annotationu girilen  degerlerin(name, age, lastname vb) column degerleri (null vb ) degerler sadece 
//bazilari icin kullanilacaksa (@RequiredArgsConstructor)'i kullanilir ama biz altta hepisine atadigimiz icin (@AllArgsConstructor)'i kullandik
@Table(name = "tbl_user")
@Entity
public class User {
	
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 25, nullable = false)
	private String firstName;
	
	@Column(length = 25, nullable = false)
	private String lastName;
	
	@Column(length = 25, nullable = false, unique = true)
	private String userName;
	
	@Column(length = 255, nullable = false)  // biz bu paswordu aldigimizda hash'lemeye girebilecegi icin karakter sayisi cok olur
	private String password;
	
	// Join kolunu yaptik bunu ayri kolon olarak gostersin ve user clasinda id  yi ("user_id") ile aldik
	// user clasi, role clasi ile join yapilacagi icin (inverseJoinColumns) ile diger classa git ve
	// oradaki class icin (name="role_id") yaparak column olustur dedik
	@JoinTable(name = "tbl_user_role",joinColumns = @JoinColumn(name="user_id"),inverseJoinColumns = @JoinColumn(name="role_id"))
	@ManyToMany(fetch = FetchType.EAGER)// buyuk projelerde LEAZY kullanmak daha iyidir ama biz burda tablomuz cok kucuk
	private Set<Role> roles = new HashSet<>();  // Burada Set kullanmamizin nedeni cunku kisi Konum degistirecekse sadece degistigi konum gozuksun
	//List yapsaydik mesela ogrenci  konumu degisip admin olacaksa hem ogrenci hem admin olarak gorunur. Set ise update yapar 
	
	// Simdi biz bu class'i yani User class'ini  Student class'i ile iliskilendirmemiz lazim
	
	
	// Spring security bizim pojo classlarimizi bilme , sadece kendi formati olan User details ile girilir.
	// bunun icin Spring security'nin User details methodunu kullanacagimiz pojo classla iliskilendirip pojo clasimizi
	// User details'e cevirecez. bunun icin islem yapmamiz lazim islemi Security classinda yapiyoruz
	
	
	
	
	
	

}
