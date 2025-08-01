#!/usr/bin/env bash
set -e

# Переходим в корень проекта
PROJECT_ROOT="$(cd "$(dirname "$0")/.."; pwd)"
cd "$PROJECT_ROOT"

echo "1️⃣ Проверяем наличие Gradle wrapper..."
if [ ! -f "./gradlew" ]; then
  echo "   Gradle wrapper не найден — инициализируем..."
  gradle wrapper
fi

# Делаем wrapper исполняемым
chmod +x ./gradlew

echo "2️⃣ Запускаем сборку Release APK..."
./gradlew clean :app:assembleRelease

APK_PATH="$PROJECT_ROOT/app/build/outputs/apk/release/NeuroAI-Offline-release.apk"
echo "✅ Сборка завершена!"
echo "   Готовый APK лежит по пути:"
echo "   $APK_PATH"
