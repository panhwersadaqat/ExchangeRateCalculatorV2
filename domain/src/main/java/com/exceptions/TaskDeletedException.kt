package com.exceptions

class TaskDeletedException(
    override var message: String = "The task has been deleted"
) : Exception()