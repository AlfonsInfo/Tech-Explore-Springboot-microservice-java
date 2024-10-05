package spring.template.learn.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;

@RestController
public class MemoryController {


    @GetMapping("/memory")
    public String getMemoryInfo() {
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        MemoryUsage heapMemoryUsage = memoryMXBean.getHeapMemoryUsage();
        MemoryUsage nonHeapMemoryUsage = memoryMXBean.getNonHeapMemoryUsage();

        return String.format("Heap Memory: %s\nNon-Heap Memory: %s",
                heapMemoryUsage.toString(), nonHeapMemoryUsage.toString());
    }
}
