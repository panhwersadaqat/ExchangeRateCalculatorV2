package com.exceptions


class UnProcessableEntityException(var errorMap: HashMap<String, String>) : Exception("Unprocessable Entity")