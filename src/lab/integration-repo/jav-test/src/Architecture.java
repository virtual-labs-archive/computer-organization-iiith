import java.awt.Graphics;

public class Architecture
{
  int main_ystart = 400;
  int main_xstart = 550;
  int cache_ystart = 400;
  int cache_xstart = 350;
  int[] cache = new int[16];
  int[] main = new int[16];
  String[] array_tags = new String[16];
  int[] array_times_FIFO = new int[16];
  int[] array_times_LRU = new int[16];
  int[] array_dirtybits = new int[16];
  int BlockSize;
  int MainMemory;
  int CacheMemory;
  int Mapping;
  int Associativity;
  int Replacement_policy;
  int Write_policy;
  int Number_CacheBlocks;
  int Number_MainBlocks;
  int Number_sets;
  
  void arrays_init()
  {
    for (int i = 0; i < 16; i++)
    {
      this.cache[i] = 16;
      this.main[i] = 16;
      this.array_times_FIFO[i] = 0;
      this.array_times_LRU[i] = 0;
      this.array_tags[i] = "";
      this.array_dirtybits[i] = 0;
    }
  }
  
  void Draw_labels(Graphics paramGraphics)
  {
    String str = "DB      TAG        CACHE                                              MAIN MEMORY";
    paramGraphics.drawString(str, 293, 390);
  }
  
  void Draw_labels_nowriteback(Graphics paramGraphics)
  {
    String str = "        TAG        CACHE                                              MAIN MEMORY";
    paramGraphics.drawString(str, 293, 390);
  }
  
  void Draw_ini_Direct(Graphics paramGraphics)
  {
    int i = 0;
    for (int j = 0; j < this.Number_CacheBlocks; j++)
    {
      paramGraphics.clearRect(this.cache_xstart - 35, this.cache_ystart + i, 34, 20);
      paramGraphics.drawRect(this.cache_xstart - 35, this.cache_ystart + i, 34, 20);
      
      paramGraphics.clearRect(this.cache_xstart, this.cache_ystart + i, 100, 20);
      paramGraphics.drawRect(this.cache_xstart, this.cache_ystart + i, 100, 20);
      paramGraphics.drawString("" + j, this.cache_xstart + 40, this.cache_ystart + i + 15);
      i += 20;
      if (j != this.Number_CacheBlocks - 1)
      {
        paramGraphics.clearRect(this.cache_xstart, this.cache_ystart + i, 100, 2);
        paramGraphics.drawRect(this.cache_xstart, this.cache_ystart + i, 100, 2);
        i += 2;
      }
    }
  }
  
  void Draw_ini_Main(Graphics paramGraphics)
  {
    int i = 0;
    for (int j = 0; j < this.Number_MainBlocks; j++)
    {
      paramGraphics.clearRect(this.main_xstart, this.main_ystart + i, 100, 20);
      paramGraphics.drawRect(this.main_xstart, this.main_ystart + i, 100, 20);
      paramGraphics.drawString("" + j, this.main_xstart + 40, this.main_ystart + i + 15);
      i += 20;
      if (j != this.Number_MainBlocks - 1)
      {
        paramGraphics.clearRect(this.main_xstart, this.main_ystart + i, 100, 2);
        paramGraphics.drawRect(this.main_xstart, this.main_ystart + i, 100, 2);
        i += 2;
      }
    }
  }
  
  void Draw_ini_setassociate(Graphics paramGraphics)
  {
    int i = 0;
    int j = 0;
    for (int k = 1; k < this.Number_CacheBlocks + 1;)
    {
      int m = this.cache_ystart + i + 10;
      paramGraphics.drawLine(this.cache_xstart + 100 + 3, this.cache_ystart + i + 10, this.cache_xstart + 100 + 10, this.cache_ystart + i + 10);
      for (int n = 0; n < this.Associativity; k++)
      {
        paramGraphics.clearRect(this.cache_xstart - 35, this.cache_ystart + i, 34, 20);
        paramGraphics.drawRect(this.cache_xstart - 35, this.cache_ystart + i, 34, 20);
        
        paramGraphics.clearRect(this.cache_xstart, this.cache_ystart + i, 100, 20);
        paramGraphics.drawRect(this.cache_xstart, this.cache_ystart + i, 100, 20);
        i += 20;n++;
      }
      paramGraphics.drawLine(this.cache_xstart + 100 + 10, m, this.cache_xstart + 100 + 10, this.cache_ystart + i - 10);
      paramGraphics.drawLine(this.cache_xstart + 100 + 3, this.cache_ystart + i - 10, this.cache_xstart + 100 + 10, this.cache_ystart + i - 10);
      paramGraphics.drawString("set " + j, this.cache_xstart + 100 + 11, (m + this.cache_ystart + i - 10) / 2);
      j += 1;
      if (k != this.Number_CacheBlocks + 1)
      {
        paramGraphics.clearRect(this.cache_xstart, this.cache_ystart + i, 100, 2);
        paramGraphics.drawRect(this.cache_xstart, this.cache_ystart + i, 100, 2);
        i += 2;
      }
    }
  }
}
