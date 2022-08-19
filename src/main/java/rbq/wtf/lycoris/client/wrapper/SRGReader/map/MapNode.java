package rbq.wtf.lycoris.client.wrapper.SRGReader.map;

public class MapNode {
    private final NodeType nodeType;
    private final String mcp;
    private final String srg;

    public MapNode(NodeType nodeType, String mcp, String srg) {
        this.nodeType = nodeType;
        this.mcp = mcp;
        this.srg = srg;
    }

    public NodeType getNodeType() {
        return nodeType;
    }

    public String getMcp() {
        return mcp;
    }

    public String getSrg() {
        return srg;
    }
}
