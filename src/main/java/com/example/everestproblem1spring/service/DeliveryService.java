package com.example.everestproblem1spring.service;

import com.example.everestproblem1spring.model.Package;
import com.example.everestproblem1spring.model.Vehicle;
import com.example.everestproblem1spring.utils.ListUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeliveryService {

    public List<Package> findPackagesForVehicle(List<Package> packageList, Vehicle vehicle) {
        List<List<Package>> possibleSubsets = ListUtils.findSubsets(packageList);

        for (int i = packageList.size(); i > 0; i--) {
            int finalI = i;
            List<List<Package>> validPackagesList = possibleSubsets.stream()
                    .filter(subset -> subset.size() == finalI)
                    .filter(list -> ListUtils.totalWeightOfPackageList(list) < vehicle.getMaxCapacity())
                    .collect(Collectors.toList());

            int validPackagesListCount = validPackagesList.size();
            if (validPackagesListCount >= 1) {
                if (validPackagesListCount == 1) {
                    return validPackagesList.get(0);
                } else {
                    List<List<Package>> sortedValidPackagesListByWeight = validPackagesList.stream().sorted((l1, l2) -> {
                        int sortCondition = ListUtils.totalWeightOfPackageList(l2) - ListUtils.totalWeightOfPackageList(l1);
                        if (sortCondition == 0) {
                            sortCondition = ListUtils.totalDistanceOfPackageList(l1) - ListUtils.totalDistanceOfPackageList(l2);
                        }
                        return sortCondition;
                    }).collect(Collectors.toList());
                    return sortedValidPackagesListByWeight.get(0);
                }
            }
        }
        return new ArrayList<>();
    }
}
