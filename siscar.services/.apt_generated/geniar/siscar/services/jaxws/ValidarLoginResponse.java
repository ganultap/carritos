
package geniar.siscar.services.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "validarLoginResponse", namespace = "http://services.siscar.geniar/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "validarLoginResponse", namespace = "http://services.siscar.geniar/")
public class ValidarLoginResponse {

    @XmlElement(name = "return", namespace = "")
    private geniar.siscar.model.VOUser _return;

    /**
     * 
     * @return
     *     returns VOUser
     */
    public geniar.siscar.model.VOUser getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(geniar.siscar.model.VOUser _return) {
        this._return = _return;
    }

}
