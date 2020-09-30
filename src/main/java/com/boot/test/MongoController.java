package com.boot.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boot.test.collections.board.Board;



@RestController
public class MongoController {
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	@RequestMapping(value = "/create/{value}")
	public Object createBoard(@PathVariable int value) {
		
		List<Board> objectsToSave = new ArrayList<Board>();
		for (int i = 0 ; i < value ; i ++) {
			Board board = new Board();
			board.setTitle("title_" + i);
			board.setSubTitle("subTitle_" + i);
			board.setContent("content_" + i);
			board.setCategory("num_"+String.valueOf(i%5));
			board.setThumbnail("thumbnail_"+i);
			board.setDeleteStatus( i % 2 == 0 ? "Y" : "N");
			board.setViewCount(String.valueOf(0));
			objectsToSave.add(board);
		}
		
		Collection<Board> insertedObject = mongoTemplate.insertAll(objectsToSave);
		
		return insertedObject;
	}

}
