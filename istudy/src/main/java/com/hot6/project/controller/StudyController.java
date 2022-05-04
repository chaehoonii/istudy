package com.hot6.project.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hot6.project.service.StudyService;
import com.hot6.project.vo.StudyVO;

@Controller
public class StudyController {
	@Inject
	StudyService Sservice;
	
	
	@GetMapping("/study/calendar/calendarList")
	public ModelAndView CalendarList(int study_num) {
		 ModelAndView mav = new ModelAndView();
		 	mav.addObject("CalendarList", Sservice.CalendarList(study_num));
	        mav.setViewName("/study/calendar/calendarList");
	        return mav;
	}
	//일정 리스트
	@ResponseBody //Ajax
	@RequestMapping(value = "/study/calendar/calendarLists", method=RequestMethod.GET)
	public List<StudyVO> calendarLists(@RequestParam("study_num")String study_num) {
		System.out.println("ajax start");
		System.out.println(study_num);
		return Sservice.CalendarList(Integer.parseInt(study_num));
	}
	
	//일정 등록
	@PostMapping("/study/calendar/calendarWrite")
	public String PlanInsert(StudyVO vo) {
		System.out.println("study_num= "+vo.getStudy_num());
		Sservice.PlanInsert(vo);
		return "redirect:/study/calendar/calendarList?study_num="+vo.getStudy_num();
	}
}
