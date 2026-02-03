package lds.com.medicalsystem.staff.labTech.service;

import lds.com.medicalsystem.staff.labTech.entity.LabItem;

public interface LabTechService {
    //
    void addLabItem(LabItem item);
    //
    void deleteLabItem(Integer itemId);
    //
    void updateLabItem(LabItem item);

}
