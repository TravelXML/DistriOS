package com.districore.platform.sfa;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class BeatPlan {
    private String beatId;
    private String beatName;
    private List<String> retailers;
    private String date;
}