package org.example.Report;

import org.example.DataModel.DataModel;

import java.util.List;

public interface Report {
    String generate(List<DataModel> entries);

    String generate(DataModel dataModel);
}
