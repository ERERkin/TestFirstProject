package com.outInformation;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class TwoProductShow {
    String name;
    int yesterdayAmountInStock;
    int todayAmountInStock;
    int yesterdayAmountSold;
    int todayAmountSold;
}
