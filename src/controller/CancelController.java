package controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CancelController {
	@RequestMapping(value = "/cancel.do")
	public String cancel(HttpServletRequest request, Model model) {
		request.getSession().invalidate();

		model.addAttribute("error", "");
		return "redirect:login.jsp";
	}
}
