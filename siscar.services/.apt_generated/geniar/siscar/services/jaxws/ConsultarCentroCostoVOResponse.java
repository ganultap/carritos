
package geniar.siscar.services.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "consultarCentroCostoVOResponse", namespace = "http://services.siscar.geniar/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "consultarCentroCostoVOResponse", namespace = "http://services.siscar.geniar/")
public class ConsultarCentroCostoVOResponse {

    @XmlElement(name = "return", namespace = "")
    private geniar.siscar.model.VOCostCenters _return;

    /**
     * 
     * @return
     *     returns VOCostCenters
     */
    public geniar.siscar.model.VOCostCenters getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(geniar.siscar.model.VOCostCenters _return) {
        this._return = _return;
    }

}
