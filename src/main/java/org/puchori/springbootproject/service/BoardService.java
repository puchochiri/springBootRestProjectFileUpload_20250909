package org.puchori.springbootproject.service;

import org.puchori.springbootproject.dto.BoardDTO;
import org.puchori.springbootproject.dto.BoardListReplyCountDTO;
import org.puchori.springbootproject.dto.PageRequestDTO;
import org.puchori.springbootproject.dto.PageResponseDTO;

public interface BoardService {
  Long register(BoardDTO boardDTO);

  BoardDTO readOne(Long bno);

  void modify(BoardDTO boardDTO);

  void remove(Long bno);

  PageResponseDTO<BoardDTO> list(PageRequestDTO pageResponseDTO);

  // 댓글의 숫자까지 처리
  PageResponseDTO<BoardListReplyCountDTO> listWithReplyCount(PageRequestDTO pageRequestDTO);


}
