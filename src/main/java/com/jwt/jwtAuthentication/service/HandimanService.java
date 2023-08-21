package com.jwt.jwtAuthentication.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.jwt.jwtAuthentication.entity.CityEntity;
import com.jwt.jwtAuthentication.entity.HandimanUserEntity;
import com.jwt.jwtAuthentication.entity.PostalCode;
import com.jwt.jwtAuthentication.entity.ServiceEntity;
import com.jwt.jwtAuthentication.entity.UserPincodeEntity;
import com.jwt.jwtAuthentication.model.City;
import com.jwt.jwtAuthentication.model.CityServiceDetails;
import com.jwt.jwtAuthentication.model.HandimanInfo;
import com.jwt.jwtAuthentication.model.HandimanPojo;
import com.jwt.jwtAuthentication.model.PinVerification;
import com.jwt.jwtAuthentication.model.ServiceParameters;
import com.jwt.jwtAuthentication.model.User;
import com.jwt.jwtAuthentication.model.UserPojo;
import com.jwt.jwtAuthentication.repo.CityRepository;
import com.jwt.jwtAuthentication.repo.HandimanUserRepository;
import com.jwt.jwtAuthentication.repo.PinCodeRepository;
import com.jwt.jwtAuthentication.repo.ServieRepository;
import com.jwt.jwtAuthentication.repo.UserPincodeRepository;
import com.jwt.jwtAuthentication.repo.UserRepository;
import com.jwt.jwtAuthentication.utils.EmailUtils;
import com.jwt.jwtAuthentication.utils.ImageStoreAwsUtils;
import com.jwt.jwtAuthentication.utils.RandomPinGenerator;

import ch.qos.logback.classic.Logger;

@Service
public class HandimanService {
	
	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private ServieRepository servieRepository;
	
	@Autowired
	private PinCodeRepository pinCodeRepository;
	
	@Autowired
	private HandimanUserRepository handimanUserRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserPincodeRepository userPincodeRepository;
	
	@Autowired
	private ImageStoreAwsUtils imageStoreAwsUtils;
	
	@Autowired
	private EmailUtils emailUtils;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Autowired
    private RandomPinGenerator randomPinGenerator;
	
	
	@Value("${project.image}")
	private String imagePath;
	
	@Value("${project.resume}")
	private String resumePath;
	
	
	
	
	public CityServiceDetails getServiceDetails() 
	{
		Map<String,List<Long>> cityPincodeMap=new HashMap<String,List<Long>>(); 
		for(CityEntity city:cityRepository.findAll()) {
			List<PostalCode> postalCodeList=pinCodeRepository.findByCity(city);
			List<Long> postalCodeList2=postalCodeList.stream().map(i -> i.getPostalCode()).collect(Collectors.toList());
			cityPincodeMap.put(city.getCityName(),postalCodeList2);
			servieRepository.findAll();
		}
		
		return new CityServiceDetails(cityPincodeMap,servieRepository.findAll());
	}
	public List<HandimanInfo> getHandimanList(ServiceParameters serviceParameters) {
		Optional<List<HandimanUserEntity>> userOptional=handimanUserRepository.findHandiman(serviceParameters.getCityName(), serviceParameters.getServiceName(),Long.parseLong(serviceParameters.getPinCode()));
		List<HandimanInfo> handimanInfoList=new ArrayList<>();
		if(userOptional.isPresent()){
			System.out.println(userOptional.get().toString());
			List<HandimanUserEntity> userList=userOptional.get();
			for(HandimanUserEntity user:userList) {
				
				//String imageUrl=generateImageUrl(user.getProfileImgUrl());
				//String resumeUrl=generateResumeUrl(user.getResumeUrl());
				handimanInfoList.add(new HandimanInfo(user.getFirstName(),user.getLastName(),user.getCity().getCityName(),user.getService().getServiceName(),user.getExperience(),user.getContactNo(),user.getEmail(),user.getProfileImgUrl(),user.getAboutMe(),user.getResumeUrl()));
			}
			
			
		}else {
			System.out.println("no user present");
		}

		return handimanInfoList;
	}
	private String generateResumeUrl(String resumeUrl) {
		// TODO Auto-generated method stub
		return null;
	}
	private InputStream generateImageUrl(String profileImgUrl) throws FileNotFoundException {
		// TODO Auto-generated method stub
		String fullPath=imagePath+File.pathSeparator+profileImgUrl;
		InputStream is=new FileInputStream(fullPath);
		return is;
	}
	public List<Long> getPostalCodeByCityName(String cityname) {
		Optional<Long> cityIdoptional=cityRepository.findByCityName(cityname);
		List<Long> pinCodeList=null;
		if(cityIdoptional.isPresent()){
			System.out.println(cityIdoptional.get());
			Long cityId=cityIdoptional.get();
			Optional<List<Long>> postalCodeListOptional=pinCodeRepository.findByCityId(cityId);
			if(postalCodeListOptional.isPresent()) {
				pinCodeList=postalCodeListOptional.get();
				return pinCodeList;
			}
		}
		return new ArrayList();
	}
	public List<String> getAllCites() {
		return cityRepository.findAllCities();
		
	}
	public boolean saveUser(UserPojo appUser) {
		if(userRepository.findByEmail(appUser.getEmail())==null && handimanUserRepository.findByEmail(appUser.getEmail())==null) {
		Long pin=(long) randomPinGenerator.generateRandomSixDigitNumber();
		User user=new User(appUser.getFirstName(),appUser.getLastName(),passwordEncoder.encode(appUser.getPassword()),appUser.getEmail(),appUser.getRole(),cityRepository.findByCityName(appUser.getCity()).get(),false,pin);
		userRepository.save(user);
		try {
			emailUtils.sendSimpleEmail(user.getEmail(), "Handiman - Pin Verification", "Your OTP : "+pin);
			}catch(Exception e) {
				System.out.println(e);
			}
		return true;
		}else {
			System.out.println("User already exist");
		return false;
		}
	}
	public boolean savehandimanUser(HandimanPojo handimanPojo) throws IOException {
		if(handimanUserRepository.findByEmail(handimanPojo.getEmail())==null && userRepository.findByEmail(handimanPojo.getEmail())==null) {
		String imageFileName=imageStoreAwsUtils.uploadFile(handimanPojo.getImageUpload(),"profile-pic/");
		String resumeFileName=imageStoreAwsUtils.uploadFile(handimanPojo.getDocumentUpload(),"resume/");
		CityEntity city=cityRepository.findByCityNameForCity(handimanPojo.getCity());
		ServiceEntity service=servieRepository.findByCityService(handimanPojo.getService());
		Long pin=(long) randomPinGenerator.generateRandomSixDigitNumber();
		HandimanUserEntity handimanUserEntity=new HandimanUserEntity(handimanPojo.getFirstName(),handimanPojo.getLastName(),city,handimanPojo.getEmail(),1.0f,Long.parseLong(handimanPojo.getContactNumber()),imageFileName,resumeFileName,handimanPojo.getAboutMe(),passwordEncoder.encode(handimanPojo.getPassword()),false,false,pin,service);
		//handimanUserRepository.save(handimanUserEntity);
		userPincodeRepository.save(new UserPincodeEntity(handimanUserRepository.save(handimanUserEntity),pinCodeRepository.findByPostalCode(Long.parseLong(handimanPojo.getPincode()))));
		try {
			emailUtils.sendSimpleEmail(handimanPojo.getEmail(), "Handiman - Pin Verification", "Your OTP : "+pin);
			}catch(Exception e) {
				System.out.println(e);
			}
		return true;
		}else {
			System.out.println("User already exist");
			return false;
		}
	}
	private String uploadImage(String imagePath, MultipartFile imageUpload) throws IOException {
		// TODO Auto-generated method stub
		//File Name
		String name=imageUpload.getOriginalFilename();
		//Full imagePath
		String randomID=UUID.randomUUID().toString();
		String FilePath1=randomID.concat(name.substring(name.lastIndexOf(".")));
		
		String fullPath=imagePath+File.pathSeparator+FilePath1;
		
		
		//create File
		File file=new File(imagePath);
		if(!file.exists()) {
			file.mkdir();
		}
		//copy file
		Files.copy(imageUpload.getInputStream(), Paths.get(fullPath));
		
		return name;
	}
	private String uploadResume(String resumePath, MultipartFile imageUpload) throws IOException {
		// TODO Auto-generated method stub
		//File Name
		String name=imageUpload.getOriginalFilename();
		//Full resumePath
		String randomID=UUID.randomUUID().toString();
		String FilePath1=randomID.concat(name.substring(name.lastIndexOf(".")));
		
		String fullPath=resumePath+File.pathSeparator+FilePath1;
		
		
		//create File
		File file=new File(resumePath);
		if(!file.exists()) {
			file.mkdir();
		}
		//copy file
		Files.copy(imageUpload.getInputStream(), Paths.get(fullPath));
		
		return name;
	}
	public List<City> homePageDetails() {
		List<CityEntity> cityEntity=cityRepository.findAll();
		List<City> cityList=new ArrayList<>();
		cityEntity.stream().map(city -> cityList.add(new City(city.getCityId(),city.getCityName(),city.getCityImage()))).collect(Collectors.toList());
		return cityList;
	}
	@Transactional
	public Object updateHandiman(HandimanPojo handimanPojo) {
		// TODO Auto-generated method stub
		Object user=null;
		System.out.println(handimanPojo.getFirstName());
		if(handimanPojo.getUserType().equals("appUser")) {
			Optional<Long> cityId=cityRepository.findByCityName(handimanPojo.getCity());
			userRepository.updateUser(handimanPojo.getEmail(),handimanPojo.getFirstName(),handimanPojo.getLastName(),cityId.get());
	        User appUser=userRepository.findByEmail(handimanPojo.getEmail());
	        if(appUser!=null) {
	        user=new UserPojo(appUser.getFirstName(),appUser.getLastName(),appUser.getEmail(),cityRepository.findById(appUser.getCityId()).get().getCityName(),appUser.getPassword(),appUser.getRole());
	        }
			return user;
		}else {
			if(handimanPojo.getImageUpload()!=null && handimanPojo.getDocumentUpload()!=null) {
				HandimanUserEntity handiman=handimanUserRepository.findByEmail(handimanPojo.getEmail());
				String imageFileName=imageStoreAwsUtils.uploadFile(handimanPojo.getImageUpload(),"profile-pic/");
				String resumeFileName=imageStoreAwsUtils.uploadFile(handimanPojo.getImageUpload(),"resume/");
//				String imageFileName="testImage";
//				String resumeFileName="TestResume";
				CityEntity city=cityRepository.findByCityNameForCity(handimanPojo.getCity());
				ServiceEntity service=servieRepository.findByCityService(handimanPojo.getService());
				HandimanUserEntity handimanUserEntity=new HandimanUserEntity(handiman.getUserId(),handimanPojo.getFirstName(),handimanPojo.getLastName(),city,handimanPojo.getEmail(),1.0f,Long.parseLong(handimanPojo.getContactNumber()),imageFileName,resumeFileName,handimanPojo.getAboutMe(),handimanPojo.getPassword(),service);
				//handimanUserRepository.save(handimanUserEntity);
				userPincodeRepository.deleteByUser(handimanUserEntity);
				userPincodeRepository.save(new UserPincodeEntity(handimanUserRepository.save(handimanUserEntity),pinCodeRepository.findByPostalCode(Long.parseLong(handimanPojo.getPincode()))));
				handiman=handimanUserRepository.findByEmail(handimanPojo.getEmail());
				List<Long> postalCodeList=userPincodeRepository.findByUserId(handiman);
				user=new HandimanInfo(handiman.getFirstName(),handiman.getLastName(),handiman.getCity().getCityName(),handiman.getService().getServiceName(),0f,handiman.getContactNo(),handiman.getEmail(),handiman.getProfileImgUrl(),handiman.getAboutMe(),postalCodeList,handiman.getRole(),handiman.getResumeUrl());
				return user;
			}else if(handimanPojo.getImageUpload()!=null && handimanPojo.getDocumentUpload()==null) {
				HandimanUserEntity handiman=handimanUserRepository.findByEmail(handimanPojo.getEmail());
				String imageFileName=imageStoreAwsUtils.uploadFile(handimanPojo.getImageUpload(),"profile-pic/");
				//String imageFileName="testImage";
				CityEntity city=cityRepository.findByCityNameForCity(handimanPojo.getCity());
				ServiceEntity service=servieRepository.findByCityService(handimanPojo.getService());
				HandimanUserEntity handimanUserEntity=new HandimanUserEntity(handiman.getUserId(),handimanPojo.getFirstName(),handimanPojo.getLastName(),city,handimanPojo.getEmail(),1.0f,Long.parseLong(handimanPojo.getContactNumber()),imageFileName,handiman.getResumeUrl(),handimanPojo.getAboutMe(),handimanPojo.getPassword(),service);
				//handimanUserRepository.save(handimanUserEntity);
				userPincodeRepository.deleteByUser(handimanUserEntity);
				userPincodeRepository.save(new UserPincodeEntity(handimanUserRepository.save(handimanUserEntity),pinCodeRepository.findByPostalCode(Long.parseLong(handimanPojo.getPincode()))));
				handiman=handimanUserRepository.findByEmail(handimanPojo.getEmail());
				List<Long> postalCodeList=userPincodeRepository.findByUserId(handiman);
				user=new HandimanInfo(handiman.getFirstName(),handiman.getLastName(),handiman.getCity().getCityName(),handiman.getService().getServiceName(),0f,handiman.getContactNo(),handiman.getEmail(),handiman.getProfileImgUrl(),handiman.getAboutMe(),postalCodeList,handiman.getRole(),handiman.getResumeUrl());
				return user;
				
			}else if(handimanPojo.getImageUpload()==null && handimanPojo.getDocumentUpload()!=null) {
				HandimanUserEntity handiman=handimanUserRepository.findByEmail(handimanPojo.getEmail());
				String resumeFileName=imageStoreAwsUtils.uploadFile(handimanPojo.getImageUpload(),"resume/");
				//String resumeFileName="TestResume";
				CityEntity city=cityRepository.findByCityNameForCity(handimanPojo.getCity());
				ServiceEntity service=servieRepository.findByCityService(handimanPojo.getService());
				HandimanUserEntity handimanUserEntity=new HandimanUserEntity(handiman.getUserId(),handimanPojo.getFirstName(),handimanPojo.getLastName(),city,handimanPojo.getEmail(),1.0f,Long.parseLong(handimanPojo.getContactNumber()),handiman.getProfileImgUrl(),resumeFileName,handimanPojo.getAboutMe(),handimanPojo.getPassword(),service);
				//handimanUserRepository.save(handimanUserEntity);
				userPincodeRepository.deleteByUser(handimanUserEntity);
				userPincodeRepository.save(new UserPincodeEntity(handimanUserRepository.save(handimanUserEntity),pinCodeRepository.findByPostalCode(Long.parseLong(handimanPojo.getPincode()))));
				handiman=handimanUserRepository.findByEmail(handimanPojo.getEmail());
				List<Long> postalCodeList=userPincodeRepository.findByUserId(handiman);
				user=new HandimanInfo(handiman.getFirstName(),handiman.getLastName(),handiman.getCity().getCityName(),handiman.getService().getServiceName(),0f,handiman.getContactNo(),handiman.getEmail(),handiman.getProfileImgUrl(),handiman.getAboutMe(),postalCodeList,handiman.getRole(),handiman.getResumeUrl());
				return user;
				
			}else if(handimanPojo.getImageUpload()==null && handimanPojo.getDocumentUpload()==null) {
				HandimanUserEntity handiman=handimanUserRepository.findByEmail(handimanPojo.getEmail());
				CityEntity city=cityRepository.findByCityNameForCity(handimanPojo.getCity());
				ServiceEntity service=servieRepository.findByCityService(handimanPojo.getService());
				HandimanUserEntity handimanUserEntity=new HandimanUserEntity(handiman.getUserId(),handimanPojo.getFirstName(),handimanPojo.getLastName(),city,handimanPojo.getEmail(),1.0f,Long.parseLong(handimanPojo.getContactNumber()),handiman.getProfileImgUrl(),handiman.getResumeUrl(),handimanPojo.getAboutMe(),handimanPojo.getPassword(),service);
				//handimanUserRepository.save(handimanUserEntity);
				userPincodeRepository.deleteByUser(handimanUserEntity);
				userPincodeRepository.save(new UserPincodeEntity(handimanUserRepository.save(handimanUserEntity),pinCodeRepository.findByPostalCode(Long.parseLong(handimanPojo.getPincode()))));
				handiman=handimanUserRepository.findByEmail(handimanPojo.getEmail());
				List<Long> postalCodeList=userPincodeRepository.findByUserId(handiman);
				user=new HandimanInfo(handiman.getFirstName(),handiman.getLastName(),handiman.getCity().getCityName(),handiman.getService().getServiceName(),0f,handiman.getContactNo(),handiman.getEmail(),handiman.getProfileImgUrl(),handiman.getAboutMe(),postalCodeList,handiman.getRole(),handiman.getResumeUrl());
				return user;
				
			}
		}
		return null;
	}
	public boolean verifyPin(PinVerification pinVerification) {
		User user=userRepository.findByEmail(pinVerification.getEmail());
		if(user!=null) {
			if(user.getPassCode().toString().equals(pinVerification.getPin())) {
				user.setActive(true);
				userRepository.save(user);
				try {
					emailUtils.sendSimpleEmail(user.getEmail(), "Welcome to handiman", "Your email verification is succesful. \n \n Thank you !");
					}catch(Exception e) {
						System.out.println(e);
					}
				return true;
			}else {
				return false;
			}
		}else if(user==null) {
		HandimanUserEntity handiman=handimanUserRepository.findByEmail(pinVerification.getEmail());
		if(handiman.getPassCode().toString().equals(pinVerification.getPin())) {
			handiman.setActive(true);
			handimanUserRepository.save(handiman);
			try {
				emailUtils.sendSimpleEmail(handiman.getEmail(), "Welcome to handiman", "Your email verification is succesfula and your profile verification is in progress \n"
						+ "once your profile get verified you will get email notification and your profile will be visible to users of your city. \n \n Thank you !");
				}catch(Exception e) {
					System.out.println(e);
				}
			return true;
		}else {
			return false;
		}
		}
		return false;
	}
	public List<HandimanInfo> getUnApprovedHandimanList() {
		List<HandimanUserEntity> userOptional=handimanUserRepository.findHandimanUnapproved();
		List<HandimanInfo> handimanInfoList=new ArrayList<>();
		for(HandimanUserEntity user:userOptional) {
			handimanInfoList.add(new HandimanInfo(user.getFirstName(),user.getLastName(),user.getCity().getCityName(),user.getService().getServiceName(),user.getExperience(),user.getContactNo(),user.getEmail(),user.getProfileImgUrl(),user.getAboutMe(),user.getResumeUrl()));
		}
		return handimanInfoList;
	}
	@Transactional
	public boolean approveUser(String email) {
		handimanUserRepository.approveUser(email);
		try {
			emailUtils.sendSimpleEmail(email, "You are verfied now", "Congratulation!! You are now verified handyman\n"
					+ "now you will be visible to the people who require your service. \n \n Thank you !");
			}catch(Exception e) {
				System.out.println(e);
			}
		return true;
	}
	public boolean forgotpassword(String email) {
		Long pin=(long) randomPinGenerator.generateRandomSixDigitNumber();
		User user=userRepository.findByEmail(email);
		if(user!=null) {
			user.setPassCode(pin);
			userRepository.save(user);
			try {
				emailUtils.sendSimpleEmail(email, "Handiman - Forgot password pin", "Your OTP for forgot pin: "+pin);
				}catch(Exception e) {
					System.out.println(e);
				}
			return true;
			}else{
				HandimanUserEntity handiman=handimanUserRepository.findByEmail(email);
				if(handiman!=null) {
					handiman.setPassCode(pin);
					handimanUserRepository.save(handiman);
					try {
						emailUtils.sendSimpleEmail(email, "Handiman - Forgot password pin", "Your OTP for forgot Password is: "+pin);
						}catch(Exception e) {
							System.out.println(e);
						}
					return true;
				}else {
					return false;
				}
				
			}
	}
}
