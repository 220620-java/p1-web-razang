package com.revature.razang.delegates;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface FrontControllerDelegate {
	public void handle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
}
