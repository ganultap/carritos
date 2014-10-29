
package geniar.siscar.services.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "validarVehiculoResponse", namespace = "http://services.siscar.geniar/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "validarVehiculoResponse", namespace = "http://services.siscar.geniar/")
public class ValidarVehiculoResponse {

    @XmlElement(name = "return", namespace = "")
    private geniar.siscar.model.VOAssignation _return;

    /**
     * 
     * @return
     *     returns VOAssignation
     */
    public geniar.siscar.model.VOAssignation getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(geniar.siscar.model.VOAssignation _return) {
        this._return = _return;
    }

}
