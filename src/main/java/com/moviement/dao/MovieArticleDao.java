package com.moviement.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.moviement.container.Container;
import com.moviement.db.DBConnection;
import com.moviement.dto.MovieArticle;

public class MovieArticleDao extends Dao {
//	public List<MovieArticles> movieArticles;
	private DBConnection dbConnection;

	public MovieArticleDao() {
//		movieArticles = new ArrayList<>();
		dbConnection = Container.getDBConnection();
	}

	public int write(MovieArticle movieArticle) {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("INSERT INTO article "));
		sb.append(String.format("SET regDate = NOW(), "));
		sb.append(String.format("updateDate = NOW(), "));
		sb.append(String.format("title = '%s', ", movieArticle.title));
		sb.append(String.format("body = '%s', ", movieArticle.body));
		sb.append(String.format("memberId = '%d', ", movieArticle.memberId));
		sb.append(String.format("boardId = '%d', ", movieArticle.boardId));
		sb.append(String.format("hit = '%d' ", movieArticle.hit));

		return dbConnection.insert(sb.toString());
	}

	public List<MovieArticle> getMovieArticles() {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("SELECT * FROM movieArticle"));

		List<MovieArticle> movieArticles = new ArrayList<>();
		List<Map<String, Object>> rows = dbConnection.selectRows(sb.toString());

		for (Map<String, Object> row : rows) {
			movieArticles.add(new MovieArticle(row));
		}
		return movieArticles;
	}

	public MovieArticle getMovieArticles(int id) {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("SELECT * "));
		sb.append(String.format("FROM article "));
		sb.append(String.format("WHERE id = '%d' ", id));

		Map<String, Object> row = dbConnection.selectRow(sb.toString());

		if (row.isEmpty()) {
			return null;
		}
		return new MovieArticle(row);
	}

//	public List<MovieArticle> getForPrintMovieArticles() {
//			List<MovieArticle> forPrintMovieArticles = new ArrayList<>();
//
//			if (searchkeyword.length() > 0) {
//				for (MovieArticle movieArticle : movieArticles) {
//					if (movieArticle.title.contains(searchkeyword)) {
//						forPrintArticles.add(movieArticle);
//					}
//				}
//			}
//			return forPrintArticles;
//		}return movieArticles;
//}

//	public Board getBoard(int id) {
//		StringBuilder sb = new StringBuilder();
//
//		sb.append(String.format("SELECT * "));
//		sb.append(String.format("FROM `board` "));
//		sb.append(String.format("WHERE id = '%d' ", id));
//
//		Map<String, Object> row = dbConnection.selectRow(sb.toString());
//
//		if (row.isEmpty()) {
//			return null;
//		}
//
//		return new Board(row);
//	}
}