
package models;

import java.io.InputStream;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

public class Order {
    int id, agentId,clientid, flyerQty, personalCopy, isFlyerArtApproved, isPaymentReceived;
    String flyerLayout, paymentInfo, comments, invoiceNo;
    Blob flyerImg;
    List<String> locations;

    public List<String> getLocations() {
        return locations;
    }

    public void setLocations(List<String> locations) {
        this.locations = locations;
    }

    public Order() {
        
    }





    public Order(int id, int agentId, int clientid, int flyerQty, int personalCopy, int isFlyerArtApproved, int isPaymentReceived, String flyerLayout, String paymentInfo, String comments, String invoiceNo, Blob flyerImg) {
        this.id = id;
        this.agentId = agentId;
        this.clientid = clientid;
        this.flyerQty = flyerQty;
        this.personalCopy = personalCopy;
        this.isFlyerArtApproved = isFlyerArtApproved;
        this.isPaymentReceived = isPaymentReceived;
        this.flyerLayout = flyerLayout;
        this.paymentInfo = paymentInfo;
        this.comments = comments;
        this.invoiceNo = invoiceNo;
        this.flyerImg = flyerImg;
    }

    public Order(int id, int agentId, int clientid, int flyerQty, int personalCopy, int isFlyerArtApproved, int isPaymentReceived, String flyerLayout, String paymentInfo, String comments, String invoiceNo) {
        this.id = id;
        this.agentId = agentId;
        this.clientid = clientid;
        this.flyerQty = flyerQty;
        this.personalCopy = personalCopy;
        this.isFlyerArtApproved = isFlyerArtApproved;
        this.isPaymentReceived = isPaymentReceived;
        this.flyerLayout = flyerLayout;
        this.paymentInfo = paymentInfo;
        this.comments = comments;
        this.invoiceNo = invoiceNo;
    }

    public Order(int agentId, int clientid, int flyerQty, int personalCopy, int isFlyerArtApproved, int isPaymentReceived, String flyerLayout, String paymentInfo, String comments, String invoiceNo) {
        this.agentId = agentId;
        this.clientid = clientid;
        this.flyerQty = flyerQty;
        this.personalCopy = personalCopy;
        this.isFlyerArtApproved = isFlyerArtApproved;
        this.isPaymentReceived = isPaymentReceived;
        this.flyerLayout = flyerLayout;
        this.paymentInfo = paymentInfo;
        this.comments = comments;
        this.invoiceNo = invoiceNo;
    }





    public int getClientid() {
        return clientid;
    }

    public void setClientid(int clientid) {
        this.clientid = clientid;
    }

    public int getIsFlyerArtApproved() {
        return isFlyerArtApproved;
    }

    public void setIsFlyerArtApproved(int isFlyerArtApproved) {
        this.isFlyerArtApproved = isFlyerArtApproved;
    }

    public int getIsPaymentReceived() {
        return isPaymentReceived;
    }

    public void setIsPaymentReceived(int isPaymentReceived) {
        this.isPaymentReceived = isPaymentReceived;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAgentId() {
        return agentId;
    }

    public void setAgentId(int agentId) {
        this.agentId = agentId;
    }

    public int getFlyerQty() {
        return flyerQty;
    }

    public void setFlyerQty(int flyerQty) {
        this.flyerQty = flyerQty;
    }

    public int getPersonalCopy() {
        return personalCopy;
    }

    public void setPersonalCopy(int personalCopy) {
        this.personalCopy = personalCopy;
    }

    public String getFlyerLayout() {
        return flyerLayout;
    }

    public void setFlyerLayout(String flyerLayout) {
        this.flyerLayout = flyerLayout;
    }

    public String getPaymentInfo() {
        return paymentInfo;
    }

    public void setPaymentInfo(String paymentInfo) {
        this.paymentInfo = paymentInfo;
    }

    public Blob getFlyerImg() {
        return flyerImg;
    }

    public void setFlyerImg(Blob flyerImg) {
        this.flyerImg = flyerImg;
    }    
    
}
