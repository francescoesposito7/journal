package com.spring.boot.journal.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.spring.boot.journal.entities.ChangedPassword;
import com.spring.boot.journal.entities.ImageUser;
import com.spring.boot.journal.entities.MotDePasseOublie;
import com.spring.boot.journal.entities.Utilisateur;
import com.spring.boot.journal.repository.RoleRepository;
import com.spring.boot.journal.repository.UtilisateurRepository;
import com.spring.boot.journal.service.ImageService;
import com.spring.boot.journal.service.RegistrationService;
import com.spring.boot.journal.service.UserService;
import com.spring.boot.journal.views.Views;


@Controller
public class UtilisateurController {
	
	@Autowired
	UtilisateurRepository repository;
	
	@Autowired
	RoleRepository roleRep;
	
	@Autowired
	private UserService userService;
	
	@Autowired
    private RegistrationService regService;
	
	@Value("${dir.images}")
	private String imageDir;
	
	@Autowired
	private ImageService imageService;

	
	@RequestMapping(value="/SaveUtilisateur",method=RequestMethod.POST)
	public String saveUtilisateur( @Valid Utilisateur user,
									BindingResult bindingResult,
									@RequestParam(name="picture") MultipartFile file) throws IllegalStateException, IOException, ParseException{		

		
		//Control si l'username est dejà utilisé
		if(userService.findUserbyUsername(user.getUsername()) != null){
			bindingResult
			.rejectValue("username", "username.user",
					"Nom d'utilisateur déjà utilisé. Veuillez utiliser un autre nom");		
		}
		
		//Control si la mail est dejà utilisé
		if ( userService.findUserbyEmail(user.getEmail()) != null){
			bindingResult
			.rejectValue("email", "email.user",
				"Email dejà utilisé. Veuillez utiliser un autre email");
		}
		
		
		if(!user.getPassword().equals(user.getPasswordConfirmation())){
			bindingResult
			.rejectValue("passwordConfirmation", "passwordConfirmation.user",
				"Password doesn't match!");
		}
		
		//Si il y a des error dans le formularie on reste la
		if(bindingResult.hasErrors()){
			return "inscriptionForm";
		
		}else{
			
			ImageUser image = new ImageUser();
			
			try {
				imageService.tempStore(file);
		        // Resize de l'image de base
				imageService.resize(file.getOriginalFilename(), false);
				// Resize pour la miniature
				imageService.resize(file.getOriginalFilename(), true);
		        // Sauvegarde de l'objet (à la fois image de base et miniature)
				image = imageService.savePhotoUser(file.getOriginalFilename());
				// Suppression des fichiers temporaires
		        imageService.tempDelete(file.getOriginalFilename());
		        
		        user.setPhoto(image);
		        
			} catch (IOException e) {
				// TODO Gérer ce message d'erreur
				e.printStackTrace();
			}
			
			
			
			userService.saveUser(user);
			regService.confirmRegistration(user);
			
			
		}
	
		return "redirect:iscriptionSuccess";
	}
	
	@RequestMapping(value="/SaveUtilisateurFacebook",method=RequestMethod.POST)
	public String saveUtilisateurFacebook( @Valid Utilisateur user,
									BindingResult bindingResult,
									@RequestParam(name="picture") MultipartFile file) throws IllegalStateException, IOException, ParseException{		

		
		//Control si l'username est dejà utilisé
		if(userService.findUserbyUsername(user.getUsername()) != null){
			bindingResult
			.rejectValue("username", "username.user",
					"Nom d'utilisateur déjà utilisé. Veuillez utiliser un autre nom");		
		}
		
		//Control si la mail est dejà utilisé
		if ( userService.findUserbyEmail(user.getEmail()) != null){
			bindingResult
			.rejectValue("email", "email.user",
				"Email dejà utilisé. Veuillez utiliser un autre email");
		}
		
		//Si il y a des error dans le formularie on reste la
		if(bindingResult.hasErrors()){
			return "inscriptionFacebook";
		
		}else{
			
			ImageUser image = new ImageUser();
			
			try {
				imageService.tempStore(file);
		        // Resize de l'image de base
				imageService.resize(file.getOriginalFilename(), false);
				// Resize pour la miniature
				imageService.resize(file.getOriginalFilename(), true);
		        // Sauvegarde de l'objet (à la fois image de base et miniature)
				image = imageService.savePhotoUser(file.getOriginalFilename());
				// Suppression des fichiers temporaires
		        imageService.tempDelete(file.getOriginalFilename());
		        user.setPhoto(image);
			} catch (IOException e) {
				// TODO Gérer ce message d'erreur
				e.printStackTrace();
			}
			
			
			
			userService.saveUser(user);
			regService.confirmRegistration(user);
		}
		
		return "redirect:iscriptionSuccess";
	}
	
	
	@PostMapping(value="/changerMotDePasse")
	public ModelAndView changerMotDePasse(HttpServletRequest request,
									@Valid ChangedPassword pass,
									BindingResult bindingResult,
									ModelAndView modelAndView){

		String remote = request.getRemoteUser();
		Utilisateur user = userService.findUserbyUsername(remote);
		
		if(!pass.getPassword().equals(pass.getConfirmationPassword())){
			bindingResult
			.rejectValue("confirmationPassword", "confirmationPassword.user",
				"Password doesn't match!");
		}
		
		if(bindingResult.hasErrors()){
			modelAndView.addObject("user", user);
			modelAndView.setViewName(Views.VIEW_PROFIL_PARAM.getPage());
			return modelAndView;
		
		}else{
				modelAndView.addObject("messagePass", "ok");
				modelAndView.addObject("user", user);
				modelAndView.setViewName(Views.VIEW_PROFIL_PARAM.getPage());
				userService.changerMotDePasse(user, pass.getPassword(), pass.getConfirmationPassword());
		}
		
		
		
		return modelAndView;
	}
	
	@PostMapping(value="/changerEmail")
	public ModelAndView changerEmail(HttpServletRequest request,
									@RequestParam(name="newEmail") String email,
									ModelAndView modelAndView){
		
		String remote = request.getRemoteUser();
		Utilisateur user = userService.findUserbyUsername(remote);
		
		modelAndView.addObject("messageEmail", "mail");
		modelAndView.addObject("changedPassword", new ChangedPassword());
		modelAndView.addObject("user", user);
		
		modelAndView.setViewName(Views.VIEW_PROFIL_PARAM.getPage());
		
		userService.changerEmail(user,email);
		
		
		return modelAndView;
	}
	
	@PostMapping(value="/resendPassword")
	public ModelAndView resendPassword(@Valid MotDePasseOublie mdpOublie,
										BindingResult bindingResult,
										ModelAndView modelAndView){
		
		//Utilisateur user = new Utilisateur();
		
		if(mdpOublie.getUsername().isEmpty()){
			System.out.println("-1-");
			bindingResult.rejectValue("username", "username.motDePasseOublie","Nom d'utilisateur vide. Veuillez inserir un username");		
		}
		
		if(userService.findUserbyUsername(mdpOublie.getUsername()) == null){
			System.out.println("-2-");
			bindingResult.rejectValue("username", "username.motDePasseOublie","Nom d'utilisateur non utilisé. Veuillez utiliser un autre nom");		
		}
		
		if(mdpOublie.getEmail().isEmpty()){
			System.out.println("-3-");
			bindingResult.rejectValue("email", "email.motDePasseOublie","Email vide. Veuillez utiliser un email");
		}
				
		if ( userService.findUserbyEmail(mdpOublie.getEmail()) == null){
			System.out.println("-4-");
			bindingResult.rejectValue("email", "email.motDePasseOublie","Email non utilisé. Veuillez utiliser un autre email");
		}
		
		if(bindingResult.hasErrors()){
			System.out.println("-5-");
			System.out.println(bindingResult.getAllErrors());
			modelAndView.addObject("mdpOublie", mdpOublie);
			modelAndView.setViewName(Views.VIEW_MOT_DE_PASSE_OUBLIE.getPage());
			return modelAndView;
		}else{
				//user = userService.findUserbyEmail(mdpOublie.getEmail());
				//modelAndView.setViewName(Views.VIEW_MOT_DE_PASSE_SUCCESS.getPage());
				//regService.resendPassword(user);
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/resetPassword", method = RequestMethod.GET)
    public ModelAndView motDePasseReset(final Locale locale, 
    								  final ModelAndView modelAndView,
    								  @RequestParam("token") final String token) throws UnsupportedEncodingException {
        
    	final String result = userService.validateVerificationToken(token);
    	final Utilisateur user = userService.getUser(token);
    	
    	if (result.equals("valid")) {
    		modelAndView.addObject("user",user);
    		
    		modelAndView.setViewName(Views.VIEW_MOT_DE_PASSE_RESET.getPage());
            return modelAndView;
        }else {
        	modelAndView.setViewName(Views.VIEW_BAD_USER.getPage());
        	 return modelAndView;
        }
    }
	
	
	@RequestMapping(value = "/resetNewPassword")
    public ModelAndView motDePasseNewReset( ModelAndView modelAndView, Utilisateur user) throws UnsupportedEncodingException {
        
    		userService.changerMotDePasse(user, user.getPassword(), user.getPasswordConfirmation());
    		modelAndView.setViewName(Views.VIEW_LOGIN.getPage());
        	 return modelAndView;
        
    }
}
