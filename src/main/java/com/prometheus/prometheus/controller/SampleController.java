package com.prometheus.prometheus.controller;

/**
 * @ClassName SampleController
 * @Description TODO
 * @Author zhangyq
 * @Date 2018/10/30 15:13
 **/
import com.prometheus.prometheus.util.HttpInvokeUtil;
import io.prometheus.client.Counter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

@RestController
public class SampleController {

    private static Random random = new Random();

    private static final Counter requestTotal = Counter.build()
            .name("my_sample_counter")
            .labelNames("status")
            .help("A simple Counter to illustrate custom Counters in Spring Boot and Prometheus").register();

    @RequestMapping("/endpoint")
    public void endpoint() {
        if (random.nextInt(2) > 0) {
            requestTotal.labels("success").inc();
        } else {
            requestTotal.labels("error").inc();
        }
    }

    @RequestMapping("/ambari")
    public String ambari() throws Exception{
        System.out.println("-------ambari------");
        String response = HttpInvokeUtil.get();
        return response;
    }


    @RequestMapping("/test")
    public String test(){
        String a = "# HELP httpsessions_max httpsessions_max\n" +
                "# TYPE httpsessions_max gauge\n" +
                "httpsessions_max -1.0\n" +
                "# HELP httpsessions_active httpsessions_active\n" +
                "# TYPE httpsessions_active gauge\n" +
                "httpsessions_active 0.0\n" +
                "# HELP mem mem\n" +
                "# TYPE mem gauge\n" +
                "mem 251898.0\n" +
                "# HELP mem_free mem_free\n" +
                "# TYPE mem_free gauge\n" +
                "mem_free 59192.0\n" +
                "# HELP processors processors\n" +
                "# TYPE processors gauge\n" +
                "processors 4.0\n" +
                "# HELP instance_uptime instance_uptime\n" +
                "# TYPE instance_uptime gauge\n" +
                "instance_uptime 391615.0\n" +
                "# HELP uptime uptime\n" +
                "# TYPE uptime gauge\n" +
                "uptime 397787.0\n" +
                "# HELP systemload_average systemload_average\n" +
                "# TYPE systemload_average gauge\n" +
                "systemload_average -1.0\n" +
                "# HELP heap_committed heap_committed\n" +
                "# TYPE heap_committed gauge\n" +
                "heap_committed 200192.0\n" +
                "# HELP heap_init heap_init\n" +
                "# TYPE heap_init gauge\n" +
                "heap_init 131072.0\n" +
                "# HELP heap_used heap_used\n" +
                "# TYPE heap_used gauge\n" +
                "heap_used 140999.0\n" +
                "# HELP heap heap\n" +
                "# TYPE heap gauge\n" +
                "heap 1842688.0\n" +
                "# HELP nonheap_committed nonheap_committed\n" +
                "# TYPE nonheap_committed gauge\n" +
                "nonheap_committed 52992.0\n" +
                "# HELP nonheap_init nonheap_init\n" +
                "# TYPE nonheap_init gauge\n" +
                "nonheap_init 2496.0\n" +
                "# HELP nonheap_used nonheap_used\n" +
                "# TYPE nonheap_used gauge\n" +
                "nonheap_used 51706.0\n" +
                "# HELP nonheap nonheap\n" +
                "# TYPE nonheap gauge\n" +
                "nonheap 0.0\n" +
                "# HELP threads_peak threads_peak\n" +
                "# TYPE threads_peak gauge\n" +
                "threads_peak 25.0\n" +
                "# HELP threads_daemon threads_daemon\n" +
                "# TYPE threads_daemon gauge\n" +
                "threads_daemon 21.0\n" +
                "# HELP threads_totalStarted threads_totalStarted\n" +
                "# TYPE threads_totalStarted gauge\n" +
                "threads_totalStarted 28.0\n" +
                "# HELP threads threads\n" +
                "# TYPE threads gauge\n" +
                "threads 23.0\n" +
                "# HELP classes classes\n" +
                "# TYPE classes gauge\n" +
                "classes 6238.0\n" +
                "# HELP classes_loaded classes_loaded\n" +
                "# TYPE classes_loaded gauge\n" +
                "classes_loaded 6238.0\n" +
                "# HELP classes_unloaded classes_unloaded\n" +
                "# TYPE classes_unloaded gauge\n" +
                "classes_unloaded 0.0\n" +
                "# HELP gc_ps_scavenge_count gc_ps_scavenge_count\n" +
                "# TYPE gc_ps_scavenge_count gauge\n" +
                "gc_ps_scavenge_count 7.0\n" +
                "# HELP gc_ps_scavenge_time gc_ps_scavenge_time\n" +
                "# TYPE gc_ps_scavenge_time gauge\n" +
                "gc_ps_scavenge_time 126.0\n" +
                "# HELP gc_ps_marksweep_count gc_ps_marksweep_count\n" +
                "# TYPE gc_ps_marksweep_count gauge\n" +
                "gc_ps_marksweep_count 1.0\n" +
                "# HELP gc_ps_marksweep_time gc_ps_marksweep_time\n" +
                "# TYPE gc_ps_marksweep_time gauge\n" +
                "gc_ps_marksweep_time 58.0\n" +
                "# HELP gauge_response_metrics gauge_response_metrics\n" +
                "# TYPE gauge_response_metrics gauge\n" +
                "gauge_response_metrics 2.0\n" +
                "# HELP gauge_response_prometheus gauge_response_prometheus\n" +
                "# TYPE gauge_response_prometheus gauge\n" +
                "gauge_response_prometheus 4.0\n" +
                "# HELP gauge_response_ambari gauge_response_ambari\n" +
                "# TYPE gauge_response_ambari gauge\n" +
                "gauge_response_ambari 2.0\n" +
                "# HELP counter_status_200_metrics counter_status_200_metrics\n" +
                "# TYPE counter_status_200_metrics gauge\n" +
                "counter_status_200_metrics 34.0\n" +
                "# HELP counter_status_200_prometheus counter_status_200_prometheus\n" +
                "# TYPE counter_status_200_prometheus gauge\n" +
                "counter_status_200_prometheus 27.0\n" +
                "# HELP counter_status_200_ambari counter_status_200_ambari\n" +
                "# TYPE counter_status_200_ambari gauge\n" +
                "counter_status_200_ambari 15.0";
        return a;
    }
}