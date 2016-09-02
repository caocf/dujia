package cn.com.gome.dujia.filter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;

import org.mozilla.javascript.ErrorReporter;
import org.mozilla.javascript.EvaluatorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yahoo.platform.yui.compressor.CssCompressor;
import com.yahoo.platform.yui.compressor.JavaScriptCompressor;

/**
 * 使用YUI Compression 压缩文件
 *
 * @author xiongyan
 * @date 2016年2月29日 上午11:14:19
 */
public class CompressionFile {

    private static final Logger logger = LoggerFactory.getLogger(CompressionFile.class);

    /**
     * js 压缩
     */
    public static class JsCompress {

        File jsFile;

        /**
         * js 压缩
         *
         * @param jsFile
         */
        public JsCompress(File jsFile) {
            this.jsFile = jsFile;
        }

        /**
         * 获取压缩后的
         *
         * @return
         */
        public String compress() {
        	BufferedReader in = null;
            StringWriter writer = null;
            try {
                // 读源文件
            	in = new BufferedReader(new InputStreamReader(new FileInputStream(this.jsFile), "UTF-8"));
                writer = new StringWriter();
                JavaScriptCompressor javaScriptCompressor = new JavaScriptCompressor(in, new JavaScriptErrorReporter());
                javaScriptCompressor.compress(writer, -1, true, false, false, false);
                return writer.getBuffer().toString();
            } catch (Exception e) {
                logger.error("JS文件【{}】压缩失败", jsFile.getAbsolutePath(), e);
                // 如果压缩失败，直接读取源文件内容（不压缩）
                return getFileContent(in);
            } finally {
                try {
                    if (in != null) {
                        in.close();
                    }
                    if (writer != null) {
                        writer.close();
                    }
                } catch (Exception e2) {
                }
            }
        }


        public static class JavaScriptErrorReporter implements ErrorReporter {

            public void warning(String message, String sourceName, int line, String lineSource, int lineOffset) {
                if (line < 0) {
                    logger.warn("[WARN] {}", message);
                } else {
                    logger.warn("[WARN] {} : {} : {}", line, lineOffset, message);
                }
            }

            public void error(String message, String sourceName, int line, String lineSource, int lineOffset) {
                if (line < 0) {
                    logger.error("[ERROR] {}", message);
                } else {
                    logger.error("[ERROR] {} : {} : {}", line, lineOffset, message);
                }
            }

            public EvaluatorException runtimeError(String message, String sourceName, int line, String lineSource, int lineOffset) {
                error(message, sourceName, line, lineSource, lineOffset);
                return new EvaluatorException(message);
            }

        }
    }


    /**
     * css 压缩
     */
    public static class CssCompress {

        File cssFile;

        /**
         * css 压缩
         *
         * @param cssFile cssFile
         */
        public CssCompress(File cssFile) {
            this.cssFile = cssFile;
        }

        /**
         * 获取压缩后的
         *
         * @return
         */
        public String compress() {
        	BufferedReader in = null;
            StringWriter writer = null;
            try {
                // 读源文件
            	in = new BufferedReader(new InputStreamReader(new FileInputStream(this.cssFile), "UTF-8"));
                writer = new StringWriter();
                CssCompressor csscompressor = new CssCompressor(in);
                csscompressor.compress(writer, -1);
                return writer.getBuffer().toString();
            } catch (Exception e) {
                logger.error("JS文件【{}】压缩失败", cssFile.getAbsolutePath(), e);
                // 如果压缩失败，直接读取源文件内容（不压缩）
                return getFileContent(in);
            } finally {
                try {
                    if (in != null) {
                        in.close();
                    }
                    if (writer != null) {
                        writer.close();
                    }
                } catch (Exception e2) {
                }
            }
        }
    }
    
    /**
     * 获取文件内容
     * @param reader
     * @return
     */
    public static String getFileContent(BufferedReader reader) {
    	if (null == reader) {
    		return null;
    	}
    	try {
            String line = null;
            StringBuilder content = new StringBuilder();
            while ((line = reader.readLine()) != null) {
            	if (content.length() > 0) {
            		content.append("\r\n");
            	}
                content.append(line);
            }
            return content.toString();
		} catch (Exception e) {
			logger.error("获取文件内容失败", e);
			return null;
		}
    }
}