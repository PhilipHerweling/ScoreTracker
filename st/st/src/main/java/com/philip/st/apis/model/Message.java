package com.philip.st.apis.model;

import lombok.*;

//I used the lombok dependency in order to cut down the amount of code
// I had to write as it saves me fromm having to write getters and setters
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Message {

    private String senderName;
    private String receiverName;
    private String message;
    private String date;
    private Status status;


}
