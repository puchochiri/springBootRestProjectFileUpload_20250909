package org.puchori.springbootproject.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.puchori.springbootproject.dto.BoardDTO;
import org.puchori.springbootproject.dto.PageRequestDTO;
import org.puchori.springbootproject.dto.PageResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class BoardServiceTests {

  @Autowired
  private BoardService boardService;

  @Test
  public void testRegister() {

    log.info(boardService.getClass().getName());

    BoardDTO boardDTO = BoardDTO.builder()
            .title("Sample Title")
            .content("Sample Content...")
            .writer("user00")
            .build();

    Long bno = boardService.register(boardDTO);

    log.info("bno: " + bno);

  }

  @Test
  public void testSelectOne() {
    log.info(boardService.getClass().getName());

    Long bno = 102L;

    BoardDTO boardDTO = boardService.readOne(bno);

    log.info(boardDTO);


  }

  @Test
  public void testModify() {

    //변경에 필요한 데이터만
    BoardDTO boardDTO = BoardDTO.builder()
            .bno(101L)
            .title("Updated....101")
            .content("Updated contented 101...")
            .build();

    boardService.modify(boardDTO);

  }

  @Test
  public void testRemove() {

    Long bno = 100L;

    boardService.remove(bno);

  }

  @Test
  public void testList() {
    PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
            .type("tcw")
            .keyword("1")
            .page(1)
            .size(10)
            .build();

    PageResponseDTO<BoardDTO> responseDTO = boardService.list(pageRequestDTO);

    log.info(responseDTO);

  }










}
