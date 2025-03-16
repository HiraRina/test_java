package jp.co.sss.test.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.sss.test.entity.User;
import jp.co.sss.test.form.LoginForm;
import jp.co.sss.test.form.UserForm;
import jp.co.sss.test.repository.UserRepository;
import jp.co.sss.test.service.ReviewService;


@Controller
public class UserController {
	
    private final UserRepository userRepository;
	private final BCryptPasswordEncoder passwordEncoder;
	
    @Autowired
    public UserController(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

	@RequestMapping("/create")
	public String createInput(Model model) {
		model.addAttribute("userForm", new UserForm());
		return"session/user";
	}
	
	//新規登録
	@RequestMapping(path = "/create/input", method = RequestMethod.POST)
	public String doCreate(@Valid @ModelAttribute UserForm form, BindingResult result, Model model ) {

		//入力エラーがないかチェック
		if(result.hasErrors()) {
			return"session/user";
		}
		
		//メールアドレス重複のチェック
		if(userRepository.findByEmail(form.getEmail()).isPresent()) {
			result.rejectValue("email","error.email" );
			return "session/user";
		}
		
		try {
			User user = new User();
			BeanUtils.copyProperties(form, user, "userId");
			
			//パスワードをハッシュ化
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			
			//DB定義でNotNullになっているのでここで登録。
			user.setUserAddress("");
			
			user= userRepository.save(user);

			return"redirect:/login";	
		} catch (Exception e) {
			model.addAttribute("errorMessage", "システムエラーが発生しました。もう一度お試しください。");
			return "session/user";
		}
	}
	
	//ログイン
	@RequestMapping(path = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		model.addAttribute("loginForm", new LoginForm());
		return "session/login";
	}

	@RequestMapping(path = "/login/complete" ,method = RequestMethod.POST)
	public String doLogin(@Valid @ModelAttribute LoginForm form,
		BindingResult result, HttpSession session, Model model) {
		
		if(result.hasErrors()) {
				return "session/login";	
			}
					
		User user = userRepository.findByEmail(form.getEmail()).orElse(null);
		//メールアドレスの照合
		if(user == null || !passwordEncoder.matches(form.getPassword(),user.getPassword())) {
			model.addAttribute("loginError", "メールアドレスまたはパスワードが間違っています");
		    return "session/login";
		}
		
		//ログイン成功
		session.setAttribute("loginUser",user);
		return "redirect:/top";
	}
}
