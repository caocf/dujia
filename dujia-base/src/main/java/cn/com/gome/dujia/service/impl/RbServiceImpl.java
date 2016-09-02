package cn.com.gome.dujia.service.impl;

import java.util.Date;
import java.util.GregorianCalendar;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import cn.com.gome.dujia.dto.ResponseDto;
import cn.com.gome.dujia.exception.BizException;
import cn.com.gome.dujia.exception.ErrorCode;
import cn.com.gome.dujia.model.RbRefund;
import cn.com.gome.dujia.service.RbService;
import cn.com.gome.dujia.ws.client.rb.AcceptRefundTask;
import cn.com.gome.dujia.ws.client.rb.RefundBankTask;
import cn.com.gome.dujia.ws.client.rb.RefundTaskReply;
/**
 * Description  : rb服务
 * Copyright    : Copyright (c) 2008- 2016 All rights reserved;
 * Created Time : 2016/4/18 12:10;
 *
 * @author  WenJie Mai
 * @version 1.0
 */
@Service
public class RbServiceImpl implements RbService{

    private static final Logger log = LoggerFactory.getLogger(RbServiceImpl.class);

    @Override
    public ResponseDto sendRb(RbRefund rb) {

        ResponseDto responseDto = new ResponseDto();
        String      code        = ErrorCode.E200.getDesc();
        String      message     = ErrorCode.E200.getDesc();

        try {
                // 推送rb参数
                RefundBankTask task = getRefundBankTask(rb);

                // 调用ws
                RefundTaskReply refundTaskReply = invokeRbWsService(rb.getAcceptRefund(),task);

                if(refundTaskReply == null){
                    throw new BizException(ErrorCode.E30002);
                }

                String state = refundTaskReply.getState();

                log.info("++++++++++++++++++sendRb_state:"+state);

                if (!"S".equalsIgnoreCase(state)) {
                    code    = ErrorCode.E30003.name();
                    message = ErrorCode.E30003.getDesc();
                }
        }catch (BizException biz) {
            code    = biz.getMessage().split(":")[0];
            message = biz.getMessage().split(":")[1];
            log.error("+++++++++++++base_sendRb_bizException_refundId:"+rb.getRefundId()+"_Exception:"+biz.getMessage(),biz);
        }catch (Exception e) {
            code    = ErrorCode.E30003.name();
            message = ErrorCode.E30003.getDesc();
            log.error("+++++++++++++base_sendRb_exception_refundId:"+rb.getRefundId()+"_Exception:"+e.getMessage(),e);
        }finally {
            responseDto.setCode(code);
            responseDto.setMessage(message);
        }
        return responseDto;
    }

    /**
     * 拼装参数
     *
     * @param  rb
     * @return
     * @author WenJie Mai
     */
    private RefundBankTask getRefundBankTask(RbRefund rb) {

        RefundBankTask task = new RefundBankTask();

        task.setOrderNo(rb.getOrderId());
        task.setPayOrderNo(rb.getPayOrderNo());
        task.setRefundNo(rb.getRefundId());
        task.setRefundAmount(rb.getRefundAmount().doubleValue());
        task.setBankType(rb.getPayModeSap());
        task.setBuid(rb.getBuid());
        task.setPayTradeNo(rb.getTransNo());
        task.setPayAmt(rb.getPayAmount().doubleValue());
        task.setCardNumber(rb.getCardNumber());
        task.setOrgSysno(rb.getOrgSysNo());
        task.setRefundDetailsId(rb.getRefundDetailsId());
        XMLGregorianCalendar calendarDate = date2XMLGregorianCalendar(rb.getPayTime());
        if( calendarDate != null){
        	task.setPayTime(calendarDate);
        }        
        
        return task;
    }

    private XMLGregorianCalendar date2XMLGregorianCalendar(Date date) {

        if (date == null) {
            date = new Date();
        }

        try {
                GregorianCalendar gc = new GregorianCalendar();
                gc.setTimeInMillis(date.getTime());
                return DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
        } catch (DatatypeConfigurationException e) {
            log.error("+++++++++++++base_date2XMLGregorianCalendar_Exception:" + e.getMessage(),e);
        }

        return null;
    }

    private RefundTaskReply invokeRbWsService(AcceptRefundTask acceptRefundTask, RefundBankTask taskBean) throws BizException {

        try {
                RefundTaskReply refundTaskReply = acceptRefundTask.acceptTask(taskBean);
                return refundTaskReply;
        } catch (Exception e) {
            log.error("+++++++++++++base_invokeRbWsService_Exception:" + e.getMessage(),e);
            throw new BizException(ErrorCode.E30002);
        }
    }


	@Override
	public AcceptRefundTask buildAcceptRefundTaskProxy(String url) throws BizException {
		try {
            JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
            factory.setAddress(url);
            return factory.create(AcceptRefundTask.class);
		} catch (Exception e) {
			log.error("+++++++++++++base_buildAcceptRefundTaskProxy_Exception:" + e.getMessage(),e);
			throw new BizException(ErrorCode.E30002);
		}
	}

}
