SRC_DIR = src
OUT_DIR = out
DOCS_DIR = docs

# Encontrar todos os arquivos .java no SRC_DIR
SOURCES := $(shell find $(SRC_DIR) -name "*.java")

# Compilar todas as classes de uma vez
all: $(SOURCES)
	mkdir -p $(OUT_DIR)
	javac -d $(OUT_DIR) $(SOURCES)

# Regra para gerar a documentação HTML usando javadoc
docs:
	mkdir -p $(DOCS_DIR)
	javadoc -d $(DOCS_DIR) $(SOURCES)

# Limpar arquivos compilados
clean:
	rm -rf $(OUT_DIR)
