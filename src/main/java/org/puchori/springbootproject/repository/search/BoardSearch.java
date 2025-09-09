package org.puchori.springbootproject.repository.search;


import org.puchori.springbootproject.domain.Board;
import org.puchori.springbootproject.dto.BoardListReplyCountDTO;
import org.puchori.springbootproject.dto.PageRequestDTO;
import org.puchori.springbootproject.dto.PageResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardSearch {

  Page<Board> search1(Pageable pageable);

  Page<Board> searchAll(String[] types, String keyword, Pageable pageable);

  Page<BoardListReplyCountDTO> searchWithReplyCount(String[] types, String keyword, Pageable pageable);



}
