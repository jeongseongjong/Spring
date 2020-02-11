package com.biz.friend.domain;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Alias("friendVO")
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class FriendVO {

	private long f_id;
	private String f_name;// VARCHAR(10) NOT NULL PRIMARY KEY,
    private String f_tel; // BIGINT,
    private String f_addr; // VARCHAR(125),
    private String f_hobby; // VARCHAR(20),
    private String f_relation; // VARCHAR(20)
	
}
