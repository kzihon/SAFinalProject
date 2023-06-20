package Springboot_kafka_realtime_producer.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecentChange {
    private String $schema;
    private Meta meta;
    private int id;
    private String type;
    private int namespace;
    private String title;
    private String title_url;
    private String comment;
    private int timestamp;
    private String user;
    private boolean bot;
    private String notify_url;
    private boolean minor;
    private boolean patrolled;
    private Length length;
    private Revision revision;
    private String server_url;
    private String server_name;
    private String server_script_path;
    private String wiki;
    private String parsedcomment;

}