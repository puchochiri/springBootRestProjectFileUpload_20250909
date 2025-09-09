package org.puchori.springbootproject.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.puchori.springbootproject.dto.BoardDTO;
import org.puchori.springbootproject.dto.BoardListReplyCountDTO;
import org.puchori.springbootproject.dto.PageRequestDTO;
import org.puchori.springbootproject.dto.PageResponseDTO;
import org.puchori.springbootproject.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/board")
@Log4j2
@RequiredArgsConstructor
public class BoardController {

  private final BoardService boardService;

  @GetMapping("/list")
  public void list(PageRequestDTO pageRequestDTO, Model model){

    //PageResponseDTO<BoardDTO> responseDTO = boardService.list(pageRequestDTO);

    PageResponseDTO<BoardListReplyCountDTO> responseDTO = boardService.listWithReplyCount(pageRequestDTO);

    log.info(responseDTO);

    model.addAttribute("responseDTO",responseDTO);



  }

  @GetMapping("/register")
  public void registerGET(){
    log.info("board Get register.......");

  }

  @PostMapping("/register")
  public String registerPost(@Valid BoardDTO boardDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes){

    log.info("board POST register.......");

    if(bindingResult.hasErrors()) {
      log.info("has erros............");
      redirectAttributes.addFlashAttribute("errors",bindingResult.getAllErrors());

      return "redirect:/board/register";


    }

    log.info(boardDTO);

    Long bno = boardService.register(boardDTO);

    redirectAttributes.addFlashAttribute("result",bno);

    return "redirect:/board/list";

  }

  @GetMapping({"/read", "/modify"})
  public void read(Long bno, PageRequestDTO pageRequestDTO, Model model){
    BoardDTO boardDTO = boardService.readOne(bno);

    log.info(boardDTO);

    model.addAttribute("dto",boardDTO);

  }

  @PostMapping("/modify")
  public String modify(PageRequestDTO pageRequestDTO,
                       @Valid BoardDTO boardDTO,
                       BindingResult bindingResult,
                       RedirectAttributes redirectAttributes) {
    log.info("board modify post......" + boardDTO);

    String link = pageRequestDTO.getLink();
    if(bindingResult.hasErrors()){
      log.info("has errors.......");



      redirectAttributes.addFlashAttribute("errors",bindingResult.getAllErrors());

      redirectAttributes.addAttribute("bno",boardDTO.getBno());

      return "redirect:/board/modify?" + link;
    }

    boardService.modify(boardDTO);

    redirectAttributes.addFlashAttribute("result","modified");

    redirectAttributes.addAttribute("bno",boardDTO.getBno());

    return "redirect:/board/read?" + link;

  }

  @PostMapping("remove")
  public String remove(Long bno,PageRequestDTO pageRequestDTO, RedirectAttributes redirectAttributes){
    log.info("remove post... " + bno);

    String link = pageRequestDTO.getLink();

    boardService.remove(bno);

    redirectAttributes.addFlashAttribute("result","removed");

    return "redirect:/board/list?" + link;
  }


}
