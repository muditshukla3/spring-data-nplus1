package com.ms.dto;

import java.util.List;

public record Post(String post, List<Comment> comments, String userName) { }
