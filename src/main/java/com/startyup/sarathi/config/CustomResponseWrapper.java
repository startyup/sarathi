package com.startyup.sarathi.config;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.WriteListener;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

public class CustomResponseWrapper extends HttpServletResponseWrapper {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintWriter writer = new PrintWriter(outputStream, true, StandardCharsets.UTF_8);

    public CustomResponseWrapper(HttpServletResponse response) {
        super(response);
    }

    @Override
    public ServletOutputStream getOutputStream() {
        return new ServletOutputStream() {
            @Override
            public boolean isReady() {
                return true;
            }

            @Override
            public void setWriteListener(WriteListener writeListener) {
            }

            @Override
            public void write(int b) throws IOException {
                outputStream.write(b);
            }
        };
    }

    @Override
    public PrintWriter getWriter() {
        return writer;
    }

    public String getContent() {
        writer.flush();
        return outputStream.toString(StandardCharsets.UTF_8);
    }

    public void copyBodyToResponse() throws IOException {
        ServletOutputStream responseOutputStream = getResponse().getOutputStream();
        responseOutputStream.write(outputStream.toByteArray());
        responseOutputStream.flush();
    }
}
