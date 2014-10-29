
package ciat.siscarMobile.services.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "validarSolicitanteResponse", namespace = "http://services.siscarMobile.ciat/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "validarSolicitanteResponse", namespace = "http://services.siscarMobile.ciat/")
public class ValidarSolicitanteResponse {

    @XmlElement(name = "return", namespace = "")
    private ciat.siscar.mobile.services.model.ValidacionSolicitante _return;

    /**
     * 
     * @return
     *     returns ValidacionSolicitante
     */
    public ciat.siscar.mobile.services.model.ValidacionSolicitante getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(ciat.siscar.mobile.services.model.ValidacionSolicitante _return) {
        this._return = _return;
    }

}
