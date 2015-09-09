package com.chemao.log.monitor.collector;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aliyun.openservices.sls.SLSClient;
import com.aliyun.openservices.sls.exception.SlsException;
import com.aliyun.openservices.sls.response.GetHistogramsResponse;

/**
 * aliyun sls 查询服务
 * @author xuhf
 * @since 2015年9月2日 下午6:24:18
 * @version V1.0 
 */
public class ALiyunSlsLogQueryServiceImpl implements LogQueryService {
	private static Logger logger = LoggerFactory.getLogger(ALiyunSlsLogQueryServiceImpl.class);
	private String accessId;
	private String accessKey;
	private String project;
	private String host = "http://cn-hangzhou.sls.aliyuncs.com";

	/*
	 * 构建一个sls client
	 */
	private SLSClient client;
	
	/**
	 * @return the accessId
	 */
	public String getAccessId() {
		return accessId;
	}

	/**
	 * @param accessId the accessId to set
	 */
	public void setAccessId(String accessId) {
		this.accessId = accessId;
	}

	/**
	 * @return the accessKey
	 */
	public String getAccessKey() {
		return accessKey;
	}

	/**
	 * @param accessKey the accessKey to set
	 */
	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}

	/**
	 * @return the project
	 */
	public String getProject() {
		return project;
	}

	/**
	 * @param project the project to set
	 */
	public void setProject(String project) {
		this.project = project;
	}

	/**
	 * @return the host
	 */
	public String getHost() {
		return host;
	}

	/**
	 * @param host the host to set
	 */
	public void setHost(String host) {
		this.host = host;
	}

	public void init() throws Exception {
		client = new SLSClient(host, accessId, accessKey);
	}
	
	/**
	 * 查询logstore的histogram信息
	 */
	public Integer getKeywordCount(String logName, String keyword, Date startTime, Date endTime) throws Exception {
		
		try {
			String query = keyword;
			String logStore = logName;
			int from = (int) (startTime.getTime() / 1000);
			int to = (int) (endTime.getTime() / 1000);
			String topic = "";
			GetHistogramsResponse histogramsResponse = client.GetHistograms(project, logStore, from, to, topic , query);
			if (histogramsResponse.IsCompleted()) {
				return (int) histogramsResponse.GetTotalCount();
			} else {
				return null;
			}
		} catch (SlsException e) {
			logger.error(String.format("CALL_SLS_API_ERROR:code=%s,msg=%s,requestId=%s", e.GetErrorCode(), e.GetErrorMessage(), e.GetRequestId()), e);
		}
		return null;
	}
}
