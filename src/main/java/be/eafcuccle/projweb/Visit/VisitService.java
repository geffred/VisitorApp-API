package be.eafcuccle.projweb.Visit;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;

public class VisitService {

    public static List<SimpleVisitDTO> toSimpleVisitDTOList(SortedSet<Visit> listVisit) {

        List<SimpleVisitDTO> simpleVisitDTOList = new ArrayList<>();

        for (Visit v : listVisit) {
            simpleVisitDTOList.add(new SimpleVisitDTO(v));
        }

        return simpleVisitDTOList;

    }
}
