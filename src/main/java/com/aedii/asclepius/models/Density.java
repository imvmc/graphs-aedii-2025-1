package com.aedii.asclepius.models;


import com.aedii.asclepius.models.enums.Day;
import com.aedii.asclepius.models.enums.Turn;

public record
Density(Day dia, Turn turn, int peopleAmount) {
}
