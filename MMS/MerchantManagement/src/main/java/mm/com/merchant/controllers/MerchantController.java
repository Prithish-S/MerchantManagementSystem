package mm.com.merchant.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import mm.com.merchant.constant.TransactConstant;
import mm.com.merchant.dto.MerchantDTO;
import mm.com.merchant.dto.PosDTO;
import mm.com.merchant.service.MerchantService;
import mm.com.merchant.service.UserService;

@Controller
@SessionAttributes("userId")
public class MerchantController {

	/* This Method lands on TerminalOnboard page.*/
	@Autowired
	UserService userService;
	
	@Autowired
	MerchantService merchantService;
	
	@GetMapping(value = "/getMerchant", produces = "application/json")
	public ResponseEntity<Object> getMerchant(@RequestParam("merchant_id") String merchant_id,Model model) throws Exception {
		model.addAttribute("module", merchantService.getMerchant(merchant_id));
		return new ResponseEntity<Object>(merchantService.getMerchant(merchant_id),HttpStatus.OK);
	}
	
	
	@PostMapping("/merKYView")
	public String getMerchantInfo(@ModelAttribute("MerchantDTO") MerchantDTO merchantDTO,Model model, HttpSession session,HttpServletRequest request)throws Exception {
		
		String methodName = "getMerchantInfo";	
		int userId= (int) session.getAttribute("userId");
		model.addAttribute("addMerchant", TransactConstant.ADDMERCHANT);
		model.addAttribute("userId", userId);
		model.addAttribute("countryNames", userService.getCountryDetail());
		return "KYMerchant";
	}
	
	@PostMapping("/addEditMerchant")
	public String getMerchant(@ModelAttribute("MerchantDTO") MerchantDTO merchantDTO,Model model, HttpSession session,HttpServletRequest request)throws Exception {
	
		System.out.println(">>>>>>>>>>>>userID>>>>>>>"+session.getAttribute("userId"));
		String methodName = "getMerchant";
		System.out.println("session user Id"+session.getAttribute("userId"));
		model.addAttribute("addMerchant", TransactConstant.ADDMERCHANT);
		model.addAttribute("showbutton",TransactConstant.ADDMERCHANT);
		model.addAttribute("merchantDTO", merchantDTO);
		model.addAttribute("requestType", TransactConstant.ADDMERCHANT);
		model.addAttribute("merchantList", merchantService.getMerchantList());
		model.addAttribute("userId",session.getAttribute("userId"));
		model.addAttribute("successStatus", "");
		model.addAttribute("errorStatus", "");
		return "MerLandingPage";
	}
	
	@PostMapping("/viewMerchant")
	public String viewMerchant(@ModelAttribute("MerchantDTO") MerchantDTO merchantDTO, HttpSession session, ModelMap model, Locale locale)throws Exception {
		
		String methodName = "viewMerchant";
		model.addAttribute("userId",session.getAttribute("userId"));
		System.out.println("Get role information from final table");
		List<MerchantDTO> merchantList=new ArrayList<MerchantDTO>();
		merchantList=merchantService.getMerchantList();
		model.addAttribute("merchantList", merchantList);
		model.addAttribute("merchantDTO", merchantDTO);
		model.addAttribute("countryNames", userService.getCountryDetail());
		model.addAttribute("addMerchant", TransactConstant.ADDMERCHANT);
		model.addAttribute("requestType", TransactConstant.ADDMERCHANT);
		model.addAttribute("showbutton",TransactConstant.ADDMERCHANT);
		return "MerchantOnboard";
	}
	
	@PostMapping("/addMerchant")
	public String addMerchant(@ModelAttribute("MerchantDTO") MerchantDTO merchantDTO,Model model, HttpSession session,HttpServletRequest request)throws Exception {
	
		String methodName = "addMerchant";	
		int userId= (int) session.getAttribute("userId");
		merchantDTO=merchantService.addMerchant(merchantDTO);
		model.addAttribute("addMerchant", TransactConstant.ADDMERCHANT);
		model.addAttribute("showbutton",TransactConstant.ADDMERCHANT);
		model.addAttribute("countryNames", userService.getCountryDetail());
		model.addAttribute("merchantDTO", merchantDTO);
		model.addAttribute("requestType", TransactConstant.ADDMERCHANT);
		model.addAttribute("merchantList", merchantService.getMerchantList());
		model.addAttribute("userId", userId);
		model.addAttribute("successStatus", "");
		model.addAttribute("errorStatus", "");
		return "MerLandingPage";
	}
	
	@PostMapping("/getPosRegForm")
	public String getPosRegForm(@ModelAttribute("PosDTO") PosDTO posDTO,Model model, HttpSession session,HttpServletRequest request)throws Exception {
	
		String methodName = "getPosRegForm";	
		int userId= (int) session.getAttribute("userId");
		//posDTO=merchantService.addMerchant(posDTO);
		model.addAttribute("addMerchant", TransactConstant.ADDMERCHANT);
		model.addAttribute("showbutton",TransactConstant.ADDMERCHANT);
		model.addAttribute("merchant_id", posDTO.getMerchant_id());
		model.addAttribute("posDTO", posDTO);
		model.addAttribute("requestType", TransactConstant.ADDMERCHANT);
		model.addAttribute("merchantList", merchantService.getMerchantList());
		model.addAttribute("userId", userId);
		model.addAttribute("successStatus", "");
		model.addAttribute("errorStatus", "");
		return "PosRegistration";
	}
	
	@PostMapping("/addPos")
	public String addPos(@ModelAttribute("PosDTO") PosDTO posDTO,Model model, HttpSession session,HttpServletRequest request)throws Exception {
	
		String methodName = "addPos";	
		int userId= (int) session.getAttribute("userId");
		posDTO=merchantService.addMerchantPos(posDTO);
		model.addAttribute("addMerchant", TransactConstant.ADDMERCHANT);
		model.addAttribute("showbutton",TransactConstant.ADDMERCHANT);
		model.addAttribute("merchant_id", posDTO.getMerchant_id());
		model.addAttribute("posDTO", posDTO);
		model.addAttribute("requestType", TransactConstant.ADDMERCHANT);
		model.addAttribute("merchantList", merchantService.getMerchantList());
		model.addAttribute("userId", userId);
		model.addAttribute("successStatus", "");
		model.addAttribute("errorStatus", "");
		return "PosRegistration";
	}
}
