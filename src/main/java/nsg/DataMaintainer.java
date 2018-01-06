/*     */
package nsg;
/*     */ 
/*     */

import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import nsg.component.Agent;
/*     */ import nsg.component.App;
/*     */ import nsg.component.Link;
/*     */ import nsg.component.Node;

/*     */
/*     */ 
/*     */ public class DataMaintainer
/*     */ {
    /*     */   public ArrayList nodes;
    /*     */   public ArrayList links;
    /*     */   public ArrayList agents;
    /*     */   public ArrayList apps;
    /*     */   public static Iterator it;

    /*     */
/*     */
    public DataMaintainer()
/*     */ {
/*  21 */
        this.nodes = new ArrayList();
/*  22 */
        this.links = new ArrayList();
/*  23 */
        this.agents = new ArrayList();
/*  24 */
        this.apps = new ArrayList();
/*     */
    }

    /*     */
/*     */
    public void removeApp(App app) {
/*  28 */
        this.apps.remove(app);
/*     */
    }

    /*     */
/*     */
    public void removeAgent(Agent agent)
/*     */ {
/*  33 */
        this.agents.remove(agent);
/*     */     
/*  35 */
        checkAgents();
/*  36 */
        checkApps();
/*     */
    }

    /*     */
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */
    public void removeLink(Link link)
/*     */ {
/*  61 */
        this.links.remove(link);
/*     */
    }

    /*     */
/*     */
    public void checkApps() {
/*  65 */
        Object[] apps = getApps();
/*     */     
/*  67 */
        for (int i = 0; i < apps.length; i++) {
/*  68 */
            App app = (App) apps[i];
/*  69 */
            if (!this.agents.contains(app.agent)) {
/*  70 */
                this.apps.remove(app);
/*     */
            }
/*     */
        }
/*     */
    }

    /*     */
/*     */
    public void checkAgents() {
/*  76 */
        Object[] agents = getAgents();
/*     */     
/*  78 */
        for (int i = 0; i < agents.length; i++) {
/*  79 */
            Agent agent = (Agent) agents[i];
/*  80 */
            if (!this.nodes.contains(agent.attachedNode)) {
/*  81 */
                this.agents.remove(agent);
/*     */
            }
/*     */
        }
/*  84 */
        for (int i = 0; i < agents.length; i++) {
/*  85 */
            Agent agent = (Agent) agents[i];
/*  86 */
            if (!this.agents.contains(agent.remoteAgent)) {
/*  87 */
                agent.remoteAgent = null;
/*     */
            }
/*     */
        }
/*     */
    }

    /*     */
/*     */
    public void checkLinks() {
/*  93 */
        Object[] links = getLinks();
/*     */     
/*  95 */
        for (int i = 0; i < links.length; i++) {
/*  96 */
            Link link = (Link) links[i];
/*  97 */
            if ((!this.nodes.contains(link.src)) || (!this.nodes.contains(link.dst))) {
/*  98 */
                this.links.remove(link);
/*     */
            }
/*     */
        }
/*     */
    }

    /*     */
/*     */
    public void removeNode(Node node) {
/* 104 */
        this.nodes.remove(node);
/*     */     
/* 106 */
        checkLinks();
/* 107 */
        checkAgents();
/* 108 */
        checkApps();
/*     */
    }

    /*     */
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */
    public Node findNode(int x, int y)
/*     */ {
/* 133 */
        it = this.nodes.iterator();
/*     */     
/* 135 */
        while (it.hasNext()) {
/* 136 */
            Node p = (Node) it.next();
/* 137 */
            if ((Math.abs(p.x - x) < 10) && (Math.abs(p.y - y) < 10)) {
/* 138 */
                return p;
/*     */
            }
/*     */
        }
/* 141 */
        return null;
/*     */
    }

    /*     */
/*     */
    public Link findLink(int x, int y) {
/* 145 */
        it = this.links.iterator();
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 150 */
        Link tempP = null;
/* 151 */
        double tempD = 0.0D;
/* 152 */
        while (it.hasNext()) {
/* 153 */
            Link linkP = (Link) it.next();
///* 154 */       boolean temp = (linkP.src.x > x ? 1 : 0) ^ (linkP.dst.x > x ? 1 : 0);
            boolean temp = ((linkP.src.x > x ? 1 : 0) ^ (linkP.dst.x > x ? 1 : 0)) > 0 ? true : false;
/* 155 */
            if (temp)
/*     */ {
/*     */ 
/* 158 */
                temp = ((linkP.src.y > y ? 1 : 0) ^ (linkP.dst.y > y ? 1 : 0)) > 0 ? true : false;
/* 159 */
                if (temp)
/*     */ {
/*     */ 
/* 162 */
                    int x1 = linkP.src.x;
/* 163 */
                    int x2 = linkP.dst.x;
/* 164 */
                    int y1 = linkP.src.y;
/* 165 */
                    int y2 = linkP.dst.y;
/* 166 */
                    double d = Math.abs((y2 - y1) * x + (x1 - x2) * y + (y1 * (x2 - x1) + x1 * (y1 - y2))) / Math.sqrt((y2 - y1) * (y2 - y1) + (x1 - x2) * (x1 - x2));
/* 167 */
                    if (d < 40.0D)
/* 168 */ if (tempP == null) {
/* 169 */
                        tempP = linkP;
/* 170 */
                        tempD = d;
/* 171 */
                    } else if (tempD > d) {
/* 172 */
                        tempP = linkP;
/* 173 */
                        tempD = d;
/*     */
                    }
/*     */
                }
/*     */
            }
        }
/* 177 */
        return tempP;
/*     */
    }

    /*     */
/*     */
    public Agent findAgent(int x, int y) {
/* 181 */
        it = this.agents.iterator();
/*     */     
/* 183 */
        while (it.hasNext()) {
/* 184 */
            Agent p = (Agent) it.next();
/* 185 */
            if ((Math.abs(p.x - x) < 10) && (Math.abs(p.y - y) < 10)) {
/* 186 */
                return p;
/*     */
            }
/*     */
        }
/* 189 */
        return null;
/*     */
    }

    /*     */
/*     */
    public App findApp(int x, int y) {
/* 193 */
        it = this.apps.iterator();
/*     */     
/* 195 */
        while (it.hasNext()) {
/* 196 */
            App p = (App) it.next();
/* 197 */
            if ((Math.abs(p.x - x) < 10) && (Math.abs(p.y - y) < 10)) {
/* 198 */
                return p;
/*     */
            }
/*     */
        }
/* 201 */
        return null;
/*     */
    }

    /*     */
/*     */
    public Object[] getNodes() {
/* 205 */
        return this.nodes.toArray();
/*     */
    }

    /*     */
/*     */
    public Object[] getLinks() {
/* 209 */
        return this.links.toArray();
/*     */
    }

    /*     */
/*     */
    public Object[] getAgents() {
/* 213 */
        return this.agents.toArray();
/*     */
    }

    /*     */
/*     */
    public Object[] getApps() {
/* 217 */
        return this.apps.toArray();
/*     */
    }
/*     */
}


/* Location:              C:\Users\Liudongdong\Desktop\NSG2.1.jar!\nsg\DataMaintainer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */