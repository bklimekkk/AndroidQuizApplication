package com.example.androidquizapplication.util

import com.example.androidquizapplication.entities.Bank

class TestUtil {
    fun createBanks(numberOfBanks: Int):MutableList<Bank>{
        var banks = mutableListOf<Bank>()
        var bankNumber = 1
        for (i in 0 until numberOfBanks){
            banks.add(Bank(
                0,
                "Bank:$bankNumber"
            ))
            bankNumber++
        }
        return banks
    }
}