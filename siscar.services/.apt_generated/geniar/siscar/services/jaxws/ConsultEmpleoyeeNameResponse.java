
package geniar.siscar.services.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "consultEmpleoyeeNameResponse", namespace = "http://services.siscar.geniar/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "consultEmpleoyeeNameResponse", namespace = "http://services.siscar.geniar/")
public class ConsultEmpleoyeeNameResponse {

    @XmlElement(name = "return", namespace = "")
    private geniar.siscar.model.VOEmployee _return;

    /**
     * 
     * @return
     *     returns VOEmployee
     */
    public geniar.siscar.model.VOEmployee getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(geniar.siscar.model.VOEmployee _return) {
        this._return = _return;
    }

}
