
package geniar.siscar.services.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "consultCostCenterFuelByPlacaResponse", namespace = "http://services.siscar.geniar/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "consultCostCenterFuelByPlacaResponse", namespace = "http://services.siscar.geniar/")
public class ConsultCostCenterFuelByPlacaResponse {

    @XmlElement(name = "return", namespace = "")
    private geniar.siscar.model.VOCostCentersFuels _return;

    /**
     * 
     * @return
     *     returns VOCostCentersFuels
     */
    public geniar.siscar.model.VOCostCentersFuels getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(geniar.siscar.model.VOCostCentersFuels _return) {
        this._return = _return;
    }

}
