
package ciat.siscarMobile.services.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "consultPumsResponse", namespace = "http://services.siscarMobile.ciat/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "consultPumsResponse", namespace = "http://services.siscarMobile.ciat/")
public class ConsultPumsResponse {

    @XmlElement(name = "return", namespace = "")
    private geniar.siscar.model.VOModel _return;

    /**
     * 
     * @return
     *     returns VOModel
     */
    public geniar.siscar.model.VOModel getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(geniar.siscar.model.VOModel _return) {
        this._return = _return;
    }

}
