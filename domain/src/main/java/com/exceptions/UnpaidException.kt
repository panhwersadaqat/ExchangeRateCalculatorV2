package com.domain.exceptions

class UnpaidException(override var message: String = "The inspection is disabled, please consult the AM/PO") :
    Exception()