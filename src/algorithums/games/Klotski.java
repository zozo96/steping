/**
 * <p>
 * 项目名：  steping
 * 文件名：  Klotski.java
 * 模块说明：
 * 修改历史：
 * 2018-10-11 - Songyanyan - 创建。
 */
package games;

import java.util.*;

/**
 * 华容道
 *
 * @author Songyanyan
 */
public class Klotski {
  /**
   * 当前滑块所在坐标(x, y)
   */
  private int x;
  private int y;
  
  /**
   * 华容道盒子
   */
  private int[][] box;
  /**
   * 标识当前盒子元素是否被搜索过
   */
  private Set<String> boxStatuses = new HashSet<String>();
  private static final String boxStatusFinal = "123456780";
  private String currentBoxStatus = "";
  
  /**
   * 方向：右下左上
   */
  private final static int[][] dir = { { 0, 1 }, { -1, 0 }, { 0, -1 }, { 1, 0 } };
  /**
   * 保存最终路径
   */
  private LinkedList list = new LinkedList<int[]>();
  
  public Klotski(int[][] box) {
    this.box = box;
    setSpace();
  }
  
  private void setSpace() {
    boolean spaceFlag = false;
    flag: for (int i = 0; i < box.length; i++) {
      for (int j = 0; j < box[i].length; j++) {
        if (box[i][j] == 0) {
          this.x = i;
          this.y = j;
          spaceFlag = true;
          break flag;
        }
      }
    }
    if (!spaceFlag) {
      System.out.println("该华容道不存在空格");
    }
  }
  
  private String getCurrentBoxStatus() {
    StringBuilder str = new StringBuilder();
    for (int i = 0; i < box.length; i++) {
      for (int j = 0; j < box[i].length; j++) {
        str.append(box[i][j]);
      }
    }
    return str.toString();
  }
  
  /**
   * 深度搜索
   */
  private boolean depthSearch(int[] simdir) {
    if (canMove(simdir)) {
      currentBoxStatus = getCurrentBoxStatus();
      if (currentBoxStatus.equals(boxStatusFinal)) {
        return true;
      }
      doMove(simdir);
      if (list.size() > 50) {
        return false;
      }
      currentBoxStatus = getCurrentBoxStatus();
      if (boxStatuses.contains(currentBoxStatus)) {
        doMoveBack(simdir);
        return false;
      }
      
      list.add(simdir);
      
      boolean searchFourOk = depthSearch(dir[0]) || depthSearch(dir[1]) || depthSearch(dir[2]) ||
        depthSearch(dir[3]);
      if (searchFourOk) {
        return true;
      } else {
        doMoveBack(simdir);
        return false;
      }
    }
    return false;
  }
  
  /**
   * 广度搜素
   */
  private void widthSearch() {
    
  }
  
  private void doMove(int[] dir) {
    int tmp = box[x + dir[0]][y + dir[1]];
    box[x][y] = tmp;
    x += dir[0];
    y += dir[1];
    box[x][y] = 0;
    
    list.add(dir);
  }
  
  private void doMoveBack(int[] dir) {
    int tmp = box[x + dir[0] * -1][y + dir[1] * -1];
    box[x][y] = tmp;
    x += dir[0] * -1;
    y += dir[1] * -1;
    box[x][y] = 0;
    list.removeLast();
  }
  
  private boolean canMove(int[] dir) {
    if (dir[0] == 0) {
      if (dir[1] == -1) {
        return (y - 1) > -1;
      } else {
        return (y + 1) < box[0].length;
      }
    } else {
      if (dir[0] == 1) {
        return (x + 1) < box.length;
      } else {
        return (x - 1) > -1;
      }
    }
  }
  
  private void doSearch() {
    for (int i = 0; i < dir.length; i++) {
      if (currentBoxStatus.equals(boxStatusFinal)) {
        break;
      }
      depthSearch(dir[i]);
    }
  }
  
  public void getResult() {
    StringBuilder sb = new StringBuilder();
    doSearch();
    if (list.isEmpty() && !currentBoxStatus.equals(boxStatusFinal)) {
      System.out.println("无解华容道！");
    } else {
      for (Object array : list) {
        int[] a = (int[]) array;
        if (a[0] == 0) {
          if (a[1] == 1) {
            sb.append(" 右");
          } else {
            sb.append(" 左");
          }
        } else {
          if (a[0] == 1) {
            sb.append(" 下");
          } else {
            sb.append(" 上");
          }
        }
      }
      System.out.println("有解！白块儿移动轨迹：" + sb.toString());
    }
  }
}
