package cy.its.syscfg.mybatis.model;

public class T_Sys_OrgWithBLOBs extends T_Sys_Org {
    private byte[] orgSignature;

    private byte[] signatureNotifier;

    public byte[] getOrgSignature() {
        return orgSignature;
    }

    public void setOrgSignature(byte[] orgSignature) {
        this.orgSignature = orgSignature;
    }

    public byte[] getSignatureNotifier() {
        return signatureNotifier;
    }

    public void setSignatureNotifier(byte[] signatureNotifier) {
        this.signatureNotifier = signatureNotifier;
    }
}