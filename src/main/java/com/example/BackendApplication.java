package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

}

// create_board(self):
// for i in range(3):
// row = []
// for j in range(3):
// row.append('_')
// self.board.append(row)