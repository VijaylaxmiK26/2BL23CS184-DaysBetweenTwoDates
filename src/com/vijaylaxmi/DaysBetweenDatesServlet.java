package com.vijaylaxmi;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/days")
public class DaysBetweenDatesServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        String start = request.getParameter("startDate");
        String end = request.getParameter("endDate");

        out.println("<html><body>");

        try {

            LocalDate d1 = LocalDate.parse(start);
            LocalDate d2 = LocalDate.parse(end);

            if (d2.isBefore(d1)) {

                out.println("<h2>Error</h2>");
                out.println("<p>End Date cannot be before Start Date.</p>");

            } else {

                long days = ChronoUnit.DAYS.between(d1, d2);

                long weeks = days / 7;
                long remainingDays = days % 7;

                out.println("<h2>Date Difference Result</h2>");
                out.println("<p>Total Days: " + days + "</p>");
                out.println("<p>Weeks: " + weeks + "</p>");
                out.println("<p>Remaining Days: " + remainingDays + "</p>");
            }

        } catch (Exception e) {

            out.println("<h2>Invalid Input</h2>");
            out.println("<p>Please enter valid dates.</p>");
        }

        out.println("</body></html>");
    }
}
