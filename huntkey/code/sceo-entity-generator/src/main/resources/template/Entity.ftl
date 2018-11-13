package ${packageStr};
import java.io.Serializable;
import java.util.*;

${importStr}

/**
 * 
 * ${entityDesc}实体
 * 
 */
public class ${className} extends ${extendName} implements Serializable {
    private static final long serialVersionUID = ${serialVersionNum};
    
${propertiesStr}
${methodStr}

}