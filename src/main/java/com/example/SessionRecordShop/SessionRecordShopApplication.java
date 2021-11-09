package com.example.SessionRecordShop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.SessionRecordShop.domain.Record;
import com.example.SessionRecordShop.domain.RecordRepository;




@SpringBootApplication
public class SessionRecordShopApplication {
	
	private static final Logger log = LoggerFactory.getLogger(SessionRecordShopApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SessionRecordShopApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner recordShop(RecordRepository recordRepository) { // , ShopCartItemRepository shopCartItemRepository
		
		return (args) -> {

			
			log.info("save some Records");
			// Record(String artist,String album, String label, int year, double price, String albumCover)
			Record record1 = new Record("Roots Manuva", "Run Come Save Me", "Big Dada", 2001, 18.00, "https://i.scdn.co/image/ab67616d0000b273b2584daa90667f5ca94eef81");
			Record record2 = new Record("Jeru the Damaja", "The Sun Rises in the East", "Payday", 1994, 16.90, "https://i.scdn.co/image/ab67616d0000b2734f2e4127a4efcc3c05b0f017");
			Record record3 = new Record("Deltron 3030", "Deltron 3030", "75 Ark Records", 2000, 21.90, "https://i.scdn.co/image/ab67616d0000b273210c8f856757abec0806209c");
			Record record4 = new Record("Dj Shadow", "Preemptive Strike", "Mo' Wax", 1998, 25.90, "https://i.scdn.co/image/ab67616d0000b27379c0803aa37d736d83d16151");
			Record record5 = new Record("Common Sense", "Resurrection", "Relativity", 1994, 23.50, "https://i.scdn.co/image/ab67616d0000b2734f6c3df7fe1ee45e0f732d76");
			Record record6 = new Record("Hieroglyphics", "3rd Eye Vision", "Hieroglyphics Imperium Recordings", 1998, 22.80, "https://i.scdn.co/image/ab67616d0000b273aa2fb750f636a934c22cdec3");
			recordRepository.save(record1);
			recordRepository.save(record2);
			recordRepository.save(record3);
			recordRepository.save(record4);
			recordRepository.save(record5);
			recordRepository.save(record6);
//			
//			ShopCartItem  shopCartItem1 = new ShopCartItem(0, 0, record1);
//			shopCartItemRepository.save(shopCartItem1);
//			ShopCartItem  shopCartItem2 = new ShopCartItem(0, 0, record2);
//			shopCartItemRepository.save(shopCartItem2);
//			ShopCartItem  shopCartItem3 = new ShopCartItem(0, 0, record3);
//			shopCartItemRepository.save(shopCartItem3);
//			ShopCartItem  shopCartItem4 = new ShopCartItem(0, 0, record4);
//			shopCartItemRepository.save(shopCartItem4);
//			ShopCartItem  shopCartItem5 = new ShopCartItem(0, 0, record5);
//			shopCartItemRepository.save(shopCartItem5);
//			ShopCartItem  shopCartItem6 = new ShopCartItem(0, 0, record6);
//			shopCartItemRepository.save(shopCartItem6);

		};
	}

}
