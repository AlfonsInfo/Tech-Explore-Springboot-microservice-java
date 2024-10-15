package com.example.utility;

import com.example.utility.unique_identifier_generator.ManualUUIDGenerator;

import java.util.UUID;

public class App
{
    public static void main( String[] args )
    {
        System.out.println(ManualUUIDGenerator.generateUUID());
        UUID.fromString(ManualUUIDGenerator.generateUUID());

    }
}
